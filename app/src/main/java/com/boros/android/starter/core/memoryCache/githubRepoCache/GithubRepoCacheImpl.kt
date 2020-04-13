package com.boros.android.starter.core.memoryCache.githubRepoCache

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.boros.android.starter.core.memoryCache.util.MemoryCacheErrorHandler
import com.boros.android.starter.core.model.GithubRepo
import com.boros.android.starter.core.repository.utils.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRepoCacheImpl @Inject constructor(
        private val errorHandler: MemoryCacheErrorHandler
) : GithubRepoCache {

    private val repoListLiveData: MutableLiveData<ArrayList<GithubRepo>> = MutableLiveData()

    override fun overrideRepositories(repositories: ArrayList<GithubRepo>?) {
        repositories?.let {
            repoListLiveData.value = it
        }
    }

    override fun getRepositories(): Result<ArrayList<GithubRepo>> {
        repoListLiveData.value.let { repositories ->
            return if (repositories != null) {
                Result.Success(repositories)
            } else {
                Result.Error(errorHandler.createGeneralError())
            }
        }
    }

    override fun getRepository(id: Int): Result<GithubRepo> {
        val repo = repoListLiveData.value?.find { it.id == id }
        return if (repo != null) {
            Result.Success(repo)
        } else {
            Result.Error(errorHandler.createGeneralError())
        }
    }

    override fun getRepositoriesLiveData(): LiveData<ArrayList<GithubRepo>> {
        return repoListLiveData
    }

}