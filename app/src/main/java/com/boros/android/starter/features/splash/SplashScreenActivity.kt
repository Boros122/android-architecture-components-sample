package com.boros.android.starter.features.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.boros.android.starter.R
import com.boros.android.starter.features.main.MainActivity
import com.boros.android.starter.shared.ui.activity.BaseActivity

class SplashScreenActivity : BaseActivity() {

    // region Properties

    private val viewModel by viewModels<SplashViewModel> { viewModelFactory }

    // endregion

    // region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainComponent.inject(this)
        setContentView(R.layout.activity_splash_screen)
        init()
    }

    // endregion

    // region Private Methods

    private fun init() {
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

    // endregion

}
