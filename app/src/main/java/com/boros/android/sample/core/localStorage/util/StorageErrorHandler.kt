package com.boros.android.sample.core.localStorage.util

import android.content.Context
import com.boros.android.sample.R
import com.boros.android.sample.core.model.ErrorResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StorageErrorHandler @Inject constructor(context: Context) {

    private val generalError: String = context.getString(R.string.storage_error_message)

    fun createGeneralError(): ErrorResponse {
        return ErrorResponse(generalError)
    }

}