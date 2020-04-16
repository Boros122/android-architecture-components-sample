package com.boros.android.sample.core.repository.utils

import android.content.Context
import com.boros.android.sample.R
import com.boros.android.sample.core.model.ErrorResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryErrorHandler @Inject constructor(context: Context) {

    private val generalError: String = context.getString(R.string.general_error_message)

    fun createGeneralError(): ErrorResponse {
        return ErrorResponse(generalError)
    }

}