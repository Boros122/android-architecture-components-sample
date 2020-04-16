package com.boros.android.sample.features.main.repoDetail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.boros.android.sample.R
import com.boros.android.sample.shared.model.ToolbarModel
import com.boros.android.sample.shared.ui.fragment.BaseFragment
import com.boros.android.sample.shared.util.GlideUtil
import com.boros.android.sample.shared.util.extensions.gone
import com.boros.android.sample.shared.util.extensions.reObserve
import com.boros.android.sample.shared.util.extensions.visible
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_repo_detail.*

class RepoDetailFragment : BaseFragment() {

    // region Properties

    private val viewModel by viewModels<RepoDetailViewModel> { viewModelFactory }

    private val errorObserver: Observer<String> = Observer { error ->
        showEmptyScreen()
        showError(error)
    }

    private val repoDetailScreenModelObserver: Observer<RepoDetailScreenModel> = Observer { model ->
        showContent()
        updateUI(model)
    }

    private fun updateUI(model: RepoDetailScreenModel) {
        Glide.with(this)
                .load(model.avatarUrl)
                .apply(GlideUtil.circleRequestOption())
                .into(ownerImageView)

        repoNameTextView?.text = model.repoName

        if (model.ownerName != null) {
            ownerNameTextView?.visible()
            ownerNameTextView?.text = model.ownerName
        } else {
            ownerNameTextView?.gone()
        }

        if (model.description != null) {
            descriptionTextView?.visible()
            descriptionTextView?.text = model.description
        } else {
            descriptionTextView?.gone()
        }
    }

    // endregion

    // region Lifecycle

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_repo_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    // endregion

    // region Private Methods

    private fun init() {
        arguments?.let { viewModel.repoId = RepoDetailFragmentArgs.fromBundle(it).repoId }
        updateToolbar(ToolbarModel(title = getString(R.string.repository), isBackIconNeeded = true))
        viewModel.requestData()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.repoDetailScreenModelLiveData.reObserve(this, repoDetailScreenModelObserver)
        viewModel.errorLiveData.reObserve(this, errorObserver)
    }

    private fun showEmptyScreen() {
        contentContainer?.gone()
        emptyScreenContainer?.visible()
    }

    private fun showContent() {
        contentContainer?.visible()
        emptyScreenContainer?.gone()
    }

    private fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    // endregion

}