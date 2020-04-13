package com.boros.android.starter.core.memoryCache.util

import android.content.Context
import com.boros.android.starter.R
import com.boros.android.starter.core.model.ErrorResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemoryCacheErrorHandler @Inject constructor(context: Context) {

    private val generalError: String = context.getString(R.string.memory_error_message)

    fun createGeneralError(): ErrorResponse {
        return ErrorResponse(generalError)
    }

}