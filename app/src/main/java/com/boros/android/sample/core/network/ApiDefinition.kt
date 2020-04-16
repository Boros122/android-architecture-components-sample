package com.boros.android.sample.core.network

import com.boros.android.sample.core.model.GithubRepo
import retrofit2.Response
import retrofit2.http.GET

interface ApiDefinition {

    @GET("repositories")
    suspend fun getRepositories(): Response<ArrayList<GithubRepo>>

}