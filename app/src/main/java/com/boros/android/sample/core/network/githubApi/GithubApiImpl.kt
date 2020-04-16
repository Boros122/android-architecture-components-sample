package com.boros.android.sample.core.network.githubApi

import com.boros.android.sample.core.model.GithubRepo
import com.boros.android.sample.core.network.ApiDefinition
import com.boros.android.sample.core.network.util.ApiErrorHandler
import com.boros.android.sample.core.repository.utils.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubApiImpl @Inject constructor(
        private val apiErrorHandler: ApiErrorHandler,
        private val apiDefinition: ApiDefinition
) : GithubApi {

    override suspend fun getRepositories(): Result<ArrayList<GithubRepo>> {
        return try {
            val response = apiDefinition.getRepositories()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                Result.Success(body)
            } else {
                val error = apiErrorHandler.createErrorResponse(response.errorBody())
                Result.Error(error)
            }
        } catch (e: Exception) {
            Result.Error(apiErrorHandler.createGeneralErrorResponse())
        }
    }

}