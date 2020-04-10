package com.boros.android.starter

import android.app.Application
import com.boros.android.starter.shared.config.ConfigurationManager
import com.boros.android.starter.core.repository.RepositoryFactory
import com.boros.android.starter.shared.sharedPreferences.SharedPreferencesManager
import com.squareup.leakcanary.LeakCanary

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        SharedPreferencesManager.init(applicationContext)
        RepositoryFactory.init(applicationContext)
        ConfigurationManager.init(assets)
        addLeakCanary()
    }

    private fun addLeakCanary() {
        if (ConfigurationManager.getMainConfiguration().isLeakCanaryEnabled) {
            if (LeakCanary.isInAnalyzerProcess(this)) {
                return
            }
            LeakCanary.install(this)
        }
    }

}