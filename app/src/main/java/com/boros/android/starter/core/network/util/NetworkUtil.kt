package com.boros.android.starter.core.network.util

import android.content.Context
import com.boros.android.starter.BuildConfig
import com.boros.android.starter.R
import com.boros.android.starter.core.localStorage.storage.sessionStorage.SessionStorage
import okhttp3.Headers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkUtil @Inject constructor(
        private val sessionStorage: SessionStorage,
        localeProvider: LocaleProvider,
        context: Context
) {

    val baseUrl = BuildConfig.BASE_URL

    private val accessTokenKey: String = context.resources.getString(R.string.access_token_key)
    private val clientLocaleKey: String = context.resources.getString(R.string.client_locale_key)
    private val contentTypeKey: String = context.resources.getString(R.string.content_type_key)
    private val acceptKey: String = context.resources.getString(R.string.accept_key)
    private val connectionKey: String = context.resources.getString(R.string.connection_key)

    private val contentTypeValue: String = context.resources.getString(R.string.content_type_value)
    private val acceptValue: String = context.resources.getString(R.string.accept_value)
    private val connectionValue: String = context.resources.getString(R.string.connection_value)
    private val clientLocaleValue: String = localeProvider.locale

    val headers: Headers
        get() {
            val headerBuilder = Headers.Builder()
                    .add(clientLocaleKey, clientLocaleValue)
                    .add(contentTypeKey, contentTypeValue)
                    .add(acceptKey, acceptValue)
                    .add(connectionKey, connectionValue)

            sessionStorage.getAccessToken()?.let {
                headerBuilder.add(accessTokenKey, it)
            }

            return headerBuilder.build()
        }

}