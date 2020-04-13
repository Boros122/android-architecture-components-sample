package com.boros.android.starter.core.repository.githubRepository

import androidx.lifecycle.LiveData
import com.boros.android.starter.core.localStorage.storage.githubRepoStorage.GithubRepoStorage
import com.boros.android.starter.core.memoryCache.githubRepoCache.GithubRepoCache
import com.boros.android.starter.core.model.GithubRepo
import com.boros.android.starter.core.network.githubApi.GithubApi
import com.boros.android.starter.core.repository.utils.RepositoryErrorHandler
import com.boros.android.starter.core.repository.utils.Result
import com.boros.android.starter.core.repository.utils.enums.RequestType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRepositoryImpl @Inject constructor(
        private val memoryCache: GithubRepoCache,
        private val localStorage: GithubRepoStorage,
        private val api: GithubApi,
        private val errorHandler: RepositoryErrorHandler
) : GithubRepository {

    override fun getGithubRepositoriesLiveData(): LiveData<ArrayList<GithubRepo>> {
        return memoryCache.getRepositoriesLiveData()
    }

    override suspend fun getGithubRepositories(requestType: RequestType): Result<ArrayList<GithubRepo>> {
        return when (requestType) {
            RequestType.MEMORY -> {
                memoryCache.getRepositories()
            }
            RequestType.STORAGE -> {
                localStorage.getRepositories()
            }
            RequestType.NETWORK -> {
                val result = api.getRepositories()
                if (result is Result.Success) {
                    updateRepositoriesCache(result.data)
                }
                result
            }
        }
    }

    override suspend fun getGithubRepository(requestType: RequestType, id: Int): Result<GithubRepo> {
        return when (requestType) {
            RequestType.MEMORY -> {
                memoryCache.getRepository(id)
            }
            RequestType.STORAGE -> {
                localStorage.getRepository(id)
            }
            RequestType.NETWORK -> {
                Result.Error(errorHandler.createGeneralError())
            }
        }
    }

    private suspend fun updateRepositoriesCache(repositories: ArrayList<GithubRepo>) {
        memoryCache.overrideRepositories(repositories)
        localStorage.saveRepositories(repositories)
    }

}