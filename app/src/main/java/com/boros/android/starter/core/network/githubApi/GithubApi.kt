package com.boros.android.starter.core.network.githubApi

import com.boros.android.starter.core.model.GithubRepo
import com.boros.android.starter.core.repository.utils.Result

interface GithubApi {

    suspend fun getRepositories(): Result<ArrayList<GithubRepo>>

}