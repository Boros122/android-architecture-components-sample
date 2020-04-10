package com.boros.android.starter.features.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.boros.android.starter.R
import com.boros.android.starter.shared.model.ToolbarModel
import com.boros.android.starter.shared.sharedPreferences.SharedPreferencesManager
import com.boros.android.starter.shared.ui.fragment.BaseFragment
import com.boros.android.starter.shared.util.manager.NavigationManager
import kotlinx.android.synthetic.main.fragment_intro.*

class IntroFragment : BaseFragment() {

    private lateinit var viewModel: IntroViewModel

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

    private fun init() {
        viewModel = ViewModelProviders.of(this).get(IntroViewModel::class.java)
        updateToolbar(ToolbarModel(title = getString(R.string.intro)))
        nextButton?.setOnClickListener {
            NavigationManager.navigateToRepoList(findNavController(this), true)
            SharedPreferencesManager.settings.isIntroPassed = true
        }
    }

}
