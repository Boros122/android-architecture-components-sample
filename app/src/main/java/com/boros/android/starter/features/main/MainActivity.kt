package com.boros.android.starter.features.main

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.Navigation
import com.boros.android.starter.R
import com.boros.android.starter.shared.broadcast.NetworkStateChangeReceiver
import com.boros.android.starter.shared.model.ToolbarModel
import com.boros.android.starter.shared.ui.activity.BaseActivity
import com.boros.android.starter.shared.util.manager.NavigationManager
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    // region Properties

    @Inject
    lateinit var navigationManager: NavigationManager

    private val viewModel by viewModels<MainViewModel> { viewModelFactory }

    private var networkStateChangeReceiver: NetworkStateChangeReceiver? = null

    // endregion

    // region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainComponent.inject(this)
        setContentView(R.layout.activity_main)
        init()
    }

    override fun onResume() {
        super.onResume()
        registerNetworkStateChangeBroadcastReceiver()
    }

    override fun onPause() {
        super.onPause()
        unregisterNetworkStateChangeBroadcastReceiver()
    }

    // endregion

    // region Private Methods

    private fun init() {
        navigationManager.updateStartDestinationIfNeeded(Navigation.findNavController(this, R.id.nav_host_fragment))
        initToolbar()
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        toolbar?.setNavigationOnClickListener { onBackPressed() }
    }

    private fun registerNetworkStateChangeBroadcastReceiver() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        networkStateChangeReceiver = NetworkStateChangeReceiver()
        registerReceiver(networkStateChangeReceiver, intentFilter)
    }

    private fun unregisterNetworkStateChangeBroadcastReceiver() {
        unregisterReceiver(networkStateChangeReceiver)
    }

    // endregion

    // region Public Methods

    fun updateToolbar(toolbarModel: ToolbarModel) {
        supportActionBar?.title = toolbarModel.title
        toolbarModel.subTitle?.let {
            supportActionBar?.subtitle = it
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(toolbarModel.isBackIconNeeded)
        supportActionBar?.setDisplayShowHomeEnabled(toolbarModel.isBackIconNeeded)
    }

    // endregion

}