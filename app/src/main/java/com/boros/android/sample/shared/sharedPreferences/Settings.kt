package com.boros.android.sample.shared.sharedPreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class Settings(context: Context) {

    private val accessTokenKey = "access_token"
    private val userLoggedInKey = "user_logged_in"
    private val isIntoPassedKey = "is_intro_passed"
    private val isAlertNotificationEnabledKey = "is_alert_notification_enabled"

    private val prefKey = "prefKey"

    private val sharedPreferences: SharedPreferences

    private val changeListener: SharedPreferences.OnSharedPreferenceChangeListener
    var loggedInStateChangeListener: LoggedInStateChangeListener? = null
    var accessTokenChangeListener: AccessTokenChangeListener? = null

    var accessToken: String?
        get() = sharedPreferences.getString(accessTokenKey, "")
        set(accessToken) {
            sharedPreferences.edit {
                putString(accessTokenKey, accessToken)
            }
        }

    var isUserLoggedIn: Boolean
        get() = sharedPreferences.getBoolean(userLoggedInKey, false)
        set(isUserLoggedIn) {
            sharedPreferences.edit {
                putBoolean(userLoggedInKey, isUserLoggedIn)
            }
        }

    var isIntroPassed: Boolean
        get() = sharedPreferences.getBoolean(isIntoPassedKey, false)
        set(isIntroPassed) {
            sharedPreferences.edit {
                putBoolean(isIntoPassedKey, isIntroPassed)
            }
        }

    var isAlertNotificationEnabled: Boolean
        get() = sharedPreferences.getBoolean(isAlertNotificationEnabledKey, false)
        set(isAlertNotificationEnabled) {
            sharedPreferences.edit {
                putBoolean(isAlertNotificationEnabledKey, isAlertNotificationEnabled)
            }
        }

    init {
        sharedPreferences = context.getSharedPreferences(prefKey, Context.MODE_PRIVATE)

        changeListener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            if (key == userLoggedInKey) {
                loggedInStateChangeListener?.onLoggedInStateChanged(isUserLoggedIn)
            }
            if (key == accessTokenKey) {
                accessTokenChangeListener?.onAccessTokenChanged(accessToken)
            }
        }

        sharedPreferences.registerOnSharedPreferenceChangeListener(changeListener)
    }

}

interface LoggedInStateChangeListener {
    fun onLoggedInStateChanged(loggedInState: Boolean)
}

interface AccessTokenChangeListener {
    fun onAccessTokenChanged(accessToken: String?)
}