package com.boros.android.starter.features.intro

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.boros.android.starter.R
import com.boros.android.starter.shared.model.ToolbarModel
import com.boros.android.starter.shared.sharedPreferences.SharedPreferencesManager
import com.boros.android.starter.shared.ui.fragment.BaseFragment
import com.boros.android.starter.shared.util.manager.NavigationManager
import kotlinx.android.synthetic.main.fragment_intro.*
import javax.inject.Inject

class IntroFragment : BaseFragment() {

    // region Properties

    @Inject
    lateinit var navigationManager: NavigationManager

    @Inject
    lateinit var sharedPreferencesManager: SharedPreferencesManager

    private val viewModel by viewModels<IntroViewModel> { viewModelFactory }

    // endregion

    // region Lifecycle

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_intro, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    // endregion

    // region Private Methods

    private fun init() {
        updateToolbar(ToolbarModel(title = getString(R.string.intro)))
        nextButton?.setOnClickListener {
            navigationManager.navigateToRepoList(findNavController(this), true)
            sharedPreferencesManager.settings.isIntroPassed = true
        }
    }

    // endregion

}
