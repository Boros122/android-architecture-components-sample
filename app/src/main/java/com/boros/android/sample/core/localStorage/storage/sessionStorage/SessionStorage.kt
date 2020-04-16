package com.boros.android.sample.core.localStorage.storage.sessionStorage

interface SessionStorage {

    fun setAccessToken(token: String?)

    fun getAccessToken(): String?

    fun setUserLoggedState(isLoggedIn: Boolean)

    fun isUserLoggedIn(): Boolean

}