package com.boros.android.starter.core.localStorage.storage.sessionStorage

interface SessionStorage {

    fun setAccessToken(token: String?)

    fun getAccessToken(): String?

    fun setUserLoggedState(isLoggedIn: Boolean)

    fun isUserLoggedIn(): Boolean

}