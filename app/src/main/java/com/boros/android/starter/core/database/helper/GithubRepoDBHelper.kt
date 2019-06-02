package com.boros.android.starter.core.database.helper

import com.boros.android.starter.core.model.GithubRepo
import com.boros.android.starter.core.repository.RepositoryFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object GithubRepoDBHelper {

    private val uiScope = CoroutineScope(Dispatchers.Main)
    private val ioScope = CoroutineScope(Dispatchers.IO)

    fun saveGithubRepo(githubRepo: GithubRepo?) {
        githubRepo ?: return
        ioScope.launch {
            RepositoryFactory.db.githubRepoDao().insertGithubRepo(githubRepo)
        }
    }

    fun saveGithubRepoList(githubRepos: ArrayList<GithubRepo>?) {
        githubRepos ?: return
        ioScope.launch {
            RepositoryFactory.db.githubRepoDao().insertGithubRepos(githubRepos)
        }
    }

    fun deleteAllGithubRepo() {
        ioScope.launch {
            RepositoryFactory.db.githubRepoDao().deleteAll()
        }
    }

    private suspend fun readGithubRepos() = withContext(Dispatchers.IO) {
        RepositoryFactory.db.githubRepoDao().getGithubRepos()
    }

    fun getGithubRepos(completion: (user: ArrayList<GithubRepo>?) -> Unit) {
        uiScope.launch {
            val githubRepos = readGithubRepos()
            completion(ArrayList(githubRepos))
        }
    }

}