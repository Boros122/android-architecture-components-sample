package com.boros.android.starter.ui.activity

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.FrameLayout
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.boros.android.starter.R
import com.boros.android.starter.broadcast.NetworkStateChangeReceiver
import com.boros.android.starter.model.ToolbarModel
import com.boros.android.starter.util.manager.NavigationManager
import com.boros.android.starter.viewModel.activity.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var viewModel: MainViewModel

    private var networkStateChangeReceiver: NetworkStateChangeReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        registerNetworkStateChangeBroadcastReceiver()
    }

    override fun onPause() {
        super.onPause()
        unregisterNetworkStateChangeBroadcastReceiver()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun init() {
        NavigationManager.updateStartDestinationIfNeeded(Navigation.findNavController(this, R.id.nav_host_fragment))
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        initToolbar()
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        toolbar?.setNavigationOnClickListener { onBackPressed() }
    }

    fun updateToolbar(toolbarModel: ToolbarModel) {
        supportActionBar?.title = toolbarModel.title
        toolbarModel.subTitle?.let {
            supportActionBar?.subtitle = it
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(toolbarModel.isBackIconNeeded)
        supportActionBar?.setDisplayShowHomeEnabled(toolbarModel.isBackIconNeeded)
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

    fun getLoadingContainer(): FrameLayout = loadingContainer

}