package com.boros.android.starter.sharedPreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class Settings(context: Context) {

    private val prefKey = "prefKey"

    private val sharedPreferences: SharedPreferences

    private val changeListener: SharedPreferences.OnSharedPreferenceChangeListener
    var loggedInStateChangeListener: LoggedInStateChangeListener? = null
    var accessTokenChangeListener: AccessTokenChangeListener? = null

    var accessToken: String?
        get() = sharedPreferences.getString(ACCESS_TOKEN, "")
        set(accessToken) {
            sharedPreferences.edit {
                putString(ACCESS_TOKEN, accessToken)
            }
        }

    var isUserLoggedIn: Boolean
        get() = sharedPreferences.getBoolean(USER_LOGGED_IN, false)
        set(isUserLoggedIn) {
            sharedPreferences.edit {
                putBoolean(USER_LOGGED_IN, isUserLoggedIn)
            }
        }

    var isIntroPassed: Boolean
        get() = sharedPreferences.getBoolean(IS_INTRO_PASSED, false)
        set(isIntroPassed) {
            sharedPreferences.edit {
                putBoolean(IS_INTRO_PASSED, isIntroPassed)
            }
        }

    var isAlertNotificationEnabled: Boolean
        get() = sharedPreferences.getBoolean(IS_ALERT_NOTIFICATION_ENABLED, false)
        set(isAlertNotificationEnabled) {
            sharedPreferences.edit {
                putBoolean(IS_ALERT_NOTIFICATION_ENABLED, isAlertNotificationEnabled)
            }
        }

    init {
        sharedPreferences = context.getSharedPreferences(prefKey, Context.MODE_PRIVATE)

        changeListener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            if (key == USER_LOGGED_IN) {
                loggedInStateChangeListener?.onLoggedInStateChanged(isUserLoggedIn)
            }
            if (key == ACCESS_TOKEN) {
                accessTokenChangeListener?.onAccessTokenChanged(accessToken)
            }
        }

        sharedPreferences.registerOnSharedPreferenceChangeListener(changeListener)
    }

    companion object {
        private val ACCESS_TOKEN = "access_token"
        private val USER_LOGGED_IN = "user_logged_in"
        private val IS_INTRO_PASSED = "is_intro_passed"
        private val IS_ALERT_NOTIFICATION_ENABLED = "is_alert_notification_enabled"
    }

}

interface LoggedInStateChangeListener {
    fun onLoggedInStateChanged(loggedInState: Boolean)
}

interface AccessTokenChangeListener {
    fun onAccessTokenChanged(accessToken: String?)
}