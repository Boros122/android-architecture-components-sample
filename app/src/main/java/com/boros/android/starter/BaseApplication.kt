package com.boros.android.starter

import android.app.Application
import com.boros.android.starter.di.components.AppComponent
import com.boros.android.starter.di.components.DaggerAppComponent
import com.boros.android.starter.shared.config.ConfigurationManager
import com.boros.android.starter.shared.sharedPreferences.SharedPreferencesManager
import com.squareup.leakcanary.LeakCanary
import javax.inject.Inject

class BaseApplication : Application() {

    @Inject
    lateinit var configurationManager: ConfigurationManager

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
        addLeakCanary()
    }

    private fun addLeakCanary() {
        if (configurationManager.getMainConfiguration().isLeakCanaryEnabled) {
            if (LeakCanary.isInAnalyzerProcess(this)) {
                return
            }
            LeakCanary.install(this)
        }
    }

}