package com.boros.android.starter.core.memoryCache.githubRepoCache

import androidx.lifecycle.LiveData
import com.boros.android.starter.core.model.GithubRepo
import com.boros.android.starter.core.repository.utils.Result

interface GithubRepoCache {

    fun overrideRepositories(repositories: ArrayList<GithubRepo>?)

    fun getRepositories(): Result<ArrayList<GithubRepo>>

    fun getRepository(id: Int): Result<GithubRepo>

    fun getRepositoriesLiveData(): LiveData<ArrayList<GithubRepo>>

}