package com.boros.android.sample.core.localStorage.storage.githubRepoStorage

import com.boros.android.sample.core.model.GithubRepo
import com.boros.android.sample.core.repository.utils.Result

interface GithubRepoStorage {

    suspend fun getRepositories(): Result<ArrayList<GithubRepo>>

    suspend fun saveRepositories(repositories: ArrayList<GithubRepo>)

    suspend fun getRepository(id: Int): Result<GithubRepo>

    suspend fun saveRepository(repository: GithubRepo)

}