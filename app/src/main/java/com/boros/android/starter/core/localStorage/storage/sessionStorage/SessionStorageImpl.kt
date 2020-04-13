package com.boros.android.starter.core.localStorage.storage.sessionStorage

import com.boros.android.starter.core.localStorage.util.StorageErrorHandler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionStorageImpl @Inject constructor(
        private val errorHandler: StorageErrorHandler,
        private val sessionPreferences: SessionSharedPreferences
) : SessionStorage {

    override fun setAccessToken(token: String?) {
        sessionPreferences.setAccessToken(token)
    }

    override fun getAccessToken(): String? {
        return sessionPreferences.getAccessToken()
    }

    override fun setUserLoggedState(isLoggedIn: Boolean) {
        sessionPreferences.setUserLoggedState(isLoggedIn)
    }

    override fun isUserLoggedIn(): Boolean {
        return sessionPreferences.isUserLoggedIn()
    }

}