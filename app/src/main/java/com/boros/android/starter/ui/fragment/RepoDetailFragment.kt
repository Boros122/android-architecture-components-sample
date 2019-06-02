package com.boros.android.starter.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.boros.android.starter.R
import com.boros.android.starter.core.ResponseWrapper
import com.boros.android.starter.core.model.GithubRepo
import com.boros.android.starter.model.ToolbarModel
import com.boros.android.starter.util.GlideUtil
import com.boros.android.starter.util.extensions.gone
import com.boros.android.starter.util.extensions.reObserve
import com.boros.android.starter.util.extensions.visible
import com.boros.android.starter.util.manager.DialogManager
import com.boros.android.starter.util.manager.LoadingManager
import com.boros.android.starter.viewModel.RepoDetailViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_repo_detail.*

class RepoDetailFragment : BaseFragment() {

    private lateinit var viewModel: RepoDetailViewModel

    private val githubRepositoryLiveDataObserver = Observer<ResponseWrapper<GithubRepo?, String?>?> {
        if (it?.data != null) {
            updateUi(it.data!!)
            LoadingManager.endLoading(context)
        } else if (it?.error != null) {
            DialogManager.showInfoDialog(context, it.error!!)
            LoadingManager.endLoading(context)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_repo_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    private fun init() {
        viewModel = ViewModelProviders.of(this).get(RepoDetailViewModel::class.java)
        arguments?.let { viewModel.repoId = RepoDetailFragmentArgs.fromBundle(it).repoId }
        updateToolbar(ToolbarModel(title = getString(R.string.repository), isBackIconNeeded = true))
        viewModel.requestData()
        addObserver()
    }

    private fun addObserver() {
        viewModel.githubRepositoryLiveData?.reObserve(this, githubRepositoryLiveDataObserver)
    }

    private fun updateUi(githubRepo: GithubRepo) {
        Glide.with(this)
                .load(githubRepo.owner?.avatarUrl)
                .apply(GlideUtil.circleRequestOption())
                .into(ownerImageView)

        repoNameTextView?.text = githubRepo.name

        if (githubRepo.owner?.login != null) {
            ownerNameTextView?.visible()
            ownerNameTextView?.text = githubRepo.owner?.login
        } else {
            ownerNameTextView?.gone()
        }

        if (githubRepo.description != null) {
            descriptionTextView?.visible()
            descriptionTextView?.text = githubRepo.description
        } else {
            descriptionTextView?.gone()
        }

    }

}