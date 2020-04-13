package com.boros.android.starter.core.network.util

import android.content.Context
import com.boros.android.starter.R
import com.boros.android.starter.core.model.ErrorResponse
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiErrorHandler @Inject constructor(context: Context, retrofit: Retrofit) {

    private val generalErrorMessage: String =
            context.getString(R.string.general_network_error_message)

    private val errorResponseConverter: Converter<ResponseBody, ErrorResponse> =
            retrofit.responseBodyConverter(ErrorResponse::class.java, arrayOfNulls(0))

    fun createErrorResponse(errorBody: ResponseBody?): ErrorResponse {
        if (errorBody?.contentType()?.subtype == "html") {
            return ErrorResponse(generalErrorMessage)
        }

        return if (errorBody != null) {
            val errorResponse: ErrorResponse? = errorResponseFromResponseBody(errorBody)
            errorResponse ?: ErrorResponse(generalErrorMessage)
        } else {
            ErrorResponse(generalErrorMessage)
        }
    }

    fun createGeneralErrorResponse(): ErrorResponse {
        return ErrorResponse(generalErrorMessage)
    }

    @Throws(IOException::class)
    private fun errorResponseFromResponseBody(response: ResponseBody): ErrorResponse {
        return try {
            errorResponseConverter.convert(response) ?: ErrorResponse(generalErrorMessage)
        } catch (e: Exception) {
            ErrorResponse(generalErrorMessage)
        }
    }

}