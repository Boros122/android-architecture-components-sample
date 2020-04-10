package com.boros.android.starter.shared.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.boros.android.starter.shared.event.InitialEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

abstract class BaseActivity : AppCompatActivity() {

    private val className = this.javaClass.name

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