package com.boros.android.starter.sharedPreferences

import android.content.Context

object SharedPreferencesManager {

    lateinit var settings: Settings

    fun init(context: Context) {
        settings = Settings(context)
    }

}