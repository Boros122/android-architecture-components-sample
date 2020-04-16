package com.boros.android.sample.core.network.githubApi

import com.boros.android.sample.core.model.GithubRepo
import com.boros.android.sample.core.repository.utils.Result

interface GithubApi {

    suspend fun getRepositories(): Result<ArrayList<GithubRepo>>

}