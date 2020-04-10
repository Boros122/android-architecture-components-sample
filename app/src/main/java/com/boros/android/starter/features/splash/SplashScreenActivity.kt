package com.boros.android.starter.features.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.boros.android.starter.R
import com.boros.android.starter.shared.ui.activity.BaseActivity
import com.boros.android.starter.features.main.MainActivity

class SplashScreenActivity : BaseActivity() {

    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        init()
    }

    private fun init() {
        viewModel = ViewModelProviders.of(this).get(SplashViewModel::class.java)
        startMainActivity()
    }

    private fun startMainActivity() {
        val mainActivityIntent = Intent(this, MainActivity::class.java)
        mainActivityIntent.action = intent.action
        mainActivityIntent.data = intent.data
        mainActivityIntent.putExtras(intent)
        this@SplashScreenActivity.startActivity(mainActivityIntent)
        this@SplashScreenActivity.finish()
    }

}
