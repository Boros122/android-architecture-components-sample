package com.boros.android.sample.core.network.util

import android.content.Context
import android.os.Build
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocaleProvider @Inject constructor(context: Context) {

    val locale: String = getLocale(context).toString()

    @Suppress("DEPRECATION")
    private fun getLocale(context: Context): Locale {
        val configuration = context.resources.configuration
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            configuration.locales.get(0)
        } else {
            configuration.locale
        }
    }

}