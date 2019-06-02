package com.boros.android.starter.core.network

import com.boros.android.starter.core.model.GithubRepo
import retrofit2.Call
import retrofit2.http.GET

interface APIDefinition {

    @GET("repositories")
    fun getRepositories(): Call<ArrayList<GithubRepo>>

}