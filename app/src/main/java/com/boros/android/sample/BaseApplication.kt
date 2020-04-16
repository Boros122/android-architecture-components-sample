package com.boros.android.sample

import android.app.Application
import com.boros.android.sample.di.components.AppComponent
import com.boros.android.sample.di.components.DaggerAppComponent
import com.boros.android.sample.shared.config.ConfigurationManager
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