package com.boros.android.starter.core.localStorage.storage.sessionStorage

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionSharedPreferences @Inject constructor(context: Context) {

    private val preferencesName = "sessionPreferences"
    private val accessTokenKey = "accessToken"
    private val userLoggedInKey = "userLoggedInState"

    private val preferences = context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE)

    fun setAccessToken(token: String?) {
        with(preferences.edit()) {
            putString(accessTokenKey, token)
            apply()
        }
    }

    fun getAccessToken(): String? {
        return preferences.getString(accessTokenKey, null)
    }

    fun setUserLoggedState(isLoggedIn: Boolean) {
        with(preferences.edit()) {
            putBoolean(userLoggedInKey, isLoggedIn)
            apply()
        }
    }

    fun isUserLoggedIn(): Boolean {
        return preferences.getBoolean(userLoggedInKey, false)
    }

}