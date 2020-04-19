package com.boros.android.sample.features.main.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.boros.android.sample.R
import com.boros.android.sample.shared.model.ToolbarModel
import com.boros.android.sample.shared.sharedPreferences.SharedPreferencesManager
import com.boros.android.sample.shared.ui.fragment.BaseFragment
import com.boros.android.sample.shared.workers.WorkUtil
import kotlinx.android.synthetic.main.fragment_settings.*
import javax.inject.Inject

class SettingsFragment : BaseFragment() {

    // region Properties

    @Inject
    lateinit var sharedPreferencesManager: SharedPreferencesManager

    @Inject
    lateinit var workUtil: WorkUtil

    private val viewModel by viewModels<SettingsViewModel> { viewModelFactory }

    // endregion

    // region Lifecycle

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    // endregion

    // region Private Methods

    private fun init() {
        updateToolbar(ToolbarModel(title = getString(R.string.settings), isBackIconNeeded = true))

        settingsItem?.setOnClickListener { }
        settingsCheckboxItem?.addCheckChangedListener(object : SettingsCheckboxItemView.CheckChangedListener {
            override fun onCheckChanged(isChecked: Boolean) {
                sharedPreferencesManager.settings.isAlertNotificationEnabled = isChecked
                if (isChecked) {
                    workUtil.scheduleAlertNotificationWork()
                } else {
                    workUtil.cancelAlertNotificationWorker()
                }
            }
        })
    }

    // endregion

}
