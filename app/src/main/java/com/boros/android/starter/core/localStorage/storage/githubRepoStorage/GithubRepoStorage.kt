package com.boros.android.starter.core.localStorage.storage.githubRepoStorage

import com.boros.android.starter.core.model.GithubRepo
import com.boros.android.starter.core.repository.utils.Result

interface GithubRepoStorage {

    suspend fun getRepositories(): Result<ArrayList<GithubRepo>>

    suspend fun saveRepositories(repositories: ArrayList<GithubRepo>)

    suspend fun getRepository(id: Int): Result<GithubRepo>

    suspend fun saveRepository(repository: GithubRepo)

}