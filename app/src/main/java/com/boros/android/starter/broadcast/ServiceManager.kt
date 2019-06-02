package com.boros.android.starter.broadcast

import android.content.Context
import android.net.ConnectivityManager

class ServiceManager(private val context: Context) {

    val isNetworkAvailable: Boolean
        get() {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = cm.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }

}