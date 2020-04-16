package com.boros.android.sample.core.localStorage.storage.githubRepoStorage

import com.boros.android.sample.core.localStorage.database.dao.GithubRepoDao
import com.boros.android.sample.core.localStorage.util.StorageErrorHandler
import com.boros.android.sample.core.model.GithubRepo
import com.boros.android.sample.core.repository.utils.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRepoStorageImpl @Inject constructor(
        private val errorHandler: StorageErrorHandler,
        private val githubRepoDao: GithubRepoDao
) : GithubRepoStorage {

    override suspend fun getRepositories(): Result<ArrayList<GithubRepo>> {
        val repositories = githubRepoDao.getGithubRepos()
        return if (repositories != null) {
            Result.Success(ArrayList(repositories))
        } else {
            Result.Error(errorHandler.createGeneralError())
        }
    }

    override suspend fun saveRepositories(repositories: ArrayList<GithubRepo>) {
        githubRepoDao.insertGithubRepos(repositories)
    }

    override suspend fun getRepository(id: Int): Result<GithubRepo> {
        val repository = githubRepoDao.getGithubRepo(id)
        return if (repository != null) {
            Result.Success(repository)
        } else {
            Result.Error(errorHandler.createGeneralError())
        }
    }

    override suspend fun saveRepository(repository: GithubRepo) {
        githubRepoDao.insertGithubRepo(repository)
    }

}