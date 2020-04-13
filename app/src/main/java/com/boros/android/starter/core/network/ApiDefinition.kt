package com.boros.android.starter.core.network

import com.boros.android.starter.core.model.GithubRepo
import retrofit2.Response
import retrofit2.http.GET

interface ApiDefinition {

    @GET("repositories")
    suspend fun getRepositories(): Response<ArrayList<GithubRepo>>

}