package com.boros.android.sample.shared.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.boros.android.sample.BaseApplication
import com.boros.android.sample.di.components.MainComponent
import com.boros.android.sample.shared.event.InitialEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    private val className = this.javaClass.name

    val mainComponent: MainComponent by lazy {
        (application as BaseApplication).appComponent.mainComponent()
                .create(supportFragmentManager)
    }

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(className, "onCreate")
        EventBus.getDefault().register(this)
    }

    override fun onStart() {
        super.onStart()
        Log.d(className, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(className, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(className, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(className, "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(className, "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
        Log.d(className, "onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(className, "onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(className, "onRestoreInstanceState")
    }

    @Subscribe
    fun initialSubscribe(event: InitialEvent) {
    }

}