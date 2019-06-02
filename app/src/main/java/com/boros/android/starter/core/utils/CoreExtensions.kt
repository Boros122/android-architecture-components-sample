package com.boros.android.starter.core.utils

import com.boros.android.starter.core.model.ErrorMessage
import com.boros.android.starter.core.repository.RepositoryFactory
import okhttp3.ResponseBody

fun ResponseBody?.message(errorMessage: String): String {
    if (this?.contentType()?.subtype() == "html") {
        return errorMessage
    }

    return if (this != null) {
        val errorResponse: ErrorMessage? = RepositoryFactory.errorResponseFromResponseBody(this)
        errorResponse?.message ?: errorMessage
    } else {
        errorMessage
    }
}