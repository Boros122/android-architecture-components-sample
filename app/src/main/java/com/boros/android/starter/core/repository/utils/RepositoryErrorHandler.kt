package com.boros.android.starter.core.repository.utils

import android.content.Context
import com.boros.android.starter.R
import com.boros.android.starter.core.model.ErrorResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryErrorHandler @Inject constructor(context: Context) {

    private val generalError: String = context.getString(R.string.general_error_message)

    fun createGeneralError(): ErrorResponse {
        return ErrorResponse(generalError)
    }

}