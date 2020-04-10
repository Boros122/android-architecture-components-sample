package com.boros.android.starter.shared.sharedPreferences

import android.content.Context

object SharedPreferencesManager {

    lateinit var settings: Settings

    fun init(context: Context) {
        settings = Settings(context)
    }

}