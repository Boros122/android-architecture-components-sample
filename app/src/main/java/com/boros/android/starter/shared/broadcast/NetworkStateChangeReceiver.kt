package com.boros.android.starter.shared.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class NetworkStateChangeReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (isNetworkAvailable(context)) {

        } else {

        }
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val serviceManager = ServiceManager(context)
        return serviceManager.isNetworkAvailable
    }
}