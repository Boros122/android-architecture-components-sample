package com.boros.android.starter.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.boros.android.starter.R
import com.boros.android.starter.adapter.RepoListRecyclerViewAdapter
import com.boros.android.starter.core.ResponseWrapper
import com.boros.android.starter.core.model.GithubRepo
import com.boros.android.starter.event.RepoListItemSelectedEvent
import com.boros.android.starter.itemModel.BaseItemModel
import com.boros.android.starter.model.ToolbarModel
import com.boros.android.starter.util.extensions.reObserve
import com.boros.android.starter.util.manager.DialogManager
import com.boros.android.starter.util.manager.LoadingManager
import com.boros.android.starter.util.manager.NavigationManager
import com.boros.android.starter.viewModel.RepoListViewModel
import kotlinx.android.synthetic.main.fragment_repo_list.*
import org.greenrobot.eventbus.Subscribe

class RepoListFragment : BaseFragment() {

    private lateinit var viewModel: RepoListViewModel
    private val adapter = RepoListRecyclerViewAdapter()

    private val githubRepositoriesLiveDataObserver = Observer<ResponseWrapper<ArrayList<GithubRepo>?, String?>?> {
        if (it?.data != null) {
            updateAdapter(viewModel.createItemModels(it.data))
            LoadingManager.endLoading(context)
        } else if (it?.error != null) {
            DialogManager.showInfoDialog(context, it.error!!)
            LoadingManager.endLoading(context)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_repo_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.settings -> {
            NavigationManager.navigateToSettings(findNavController(this))
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun init() {
        activity?.run {
            viewModel = ViewModelProviders.of(this).get(RepoListViewModel::class.java)
            updateToolbar(ToolbarModel(title = getString(R.string.repositories)))
            initRecyclerView()
            viewModel.requestData()
            addObserver()
            LoadingManager.startLoading(context)
        }
    }

    private fun initRecyclerView() {
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

    private fun addObserver() {
        viewModel.githubRepositoriesLiveData?.reObserve(this, githubRepositoriesLiveDataObserver)
    }

    private fun updateAdapter(items: ArrayList<BaseItemModel>) {
        adapter.clear()
        adapter.addAll(items)
        adapter.notifyDataSetChanged()
    }

    @Subscribe
    fun onRepoListItemSelectedEvent(event: RepoListItemSelectedEvent) {
        NavigationManager.navigateToRepoDetail(findNavController(this), event.id)
    }

}