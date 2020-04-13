package com.boros.android.starter.features.main.repoList

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.boros.android.starter.R
import com.boros.android.starter.shared.model.ToolbarModel
import com.boros.android.starter.shared.ui.fragment.BaseFragment
import com.boros.android.starter.shared.ui.recyclerView.OnItemClickListener
import com.boros.android.starter.shared.util.extensions.gone
import com.boros.android.starter.shared.util.extensions.reObserve
import com.boros.android.starter.shared.util.extensions.visible
import com.boros.android.starter.shared.util.manager.LoadingManager
import com.boros.android.starter.shared.util.manager.NavigationManager
import kotlinx.android.synthetic.main.fragment_repo_list.*
import javax.inject.Inject

class RepoListFragment : BaseFragment() {

    // region Properties

    @Inject
    lateinit var navigationManager: NavigationManager

    @Inject
    lateinit var loadingManager: LoadingManager

    private val viewModel by viewModels<RepoListViewModel> { viewModelFactory }

    private val recyclerAdapter: RepoListRecyclerViewAdapter by lazy {
        RepoListRecyclerViewAdapter()
    }

    private val errorObserver: Observer<String> = Observer { error ->
        showEmptyScreen()
        showError(error)
    }

    private val loadingObserver: Observer<Boolean> = Observer { isLoading ->
        if (isLoading) {
            loadingManager.startTransparentLoading(context)
        } else {
            loadingManager.endLoading(context)
        }
    }

    private val cellModelObserver: Observer<ArrayList<RepoCellModel>> = Observer { items ->
        if (items.isEmpty()) {
            showEmptyScreen()
        } else {
            showContent()
            updateAdapter(items)
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
        return inflater.inflate(R.layout.fragment_repo_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.settings -> {
            navigationManager.navigateToSettings(findNavController(this))
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    // endregion

    // region Private Methods

    private fun init() {
        updateToolbar(ToolbarModel(title = getString(R.string.repositories)))
        initRecyclerView()
        setupObservers()
        setupSwipeRefreshListener()
        if (!viewModel.isDataAvailable()) {
            viewModel.fetchData()
        }
    }

    private fun setupSwipeRefreshListener() {
        swipeRefreshLayout?.setOnRefreshListener {
            viewModel.fetchData()
        }
    }

    private fun setupObservers() {
        viewModel.errorLiveData.reObserve(this, errorObserver)
        viewModel.loadingLiveData.reObserve(this, loadingObserver)
        viewModel.cellModelLiveData.reObserve(this, cellModelObserver)
    }

    private fun initRecyclerView() {
        initAdapter()
        recyclerView?.apply {
            setHasFixedSize(true)
            adapter = recyclerAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun initAdapter() {
        val clickListener = object : OnItemClickListener {
            override fun onItemClicked(id: String) {
                navigationManager.navigateToRepoDetail(findNavController(this@RepoListFragment), id.toInt())
            }
        }
        recyclerAdapter.addItemClickListener(clickListener)
    }

    private fun showEmptyScreen() {
        swipeRefreshLayout?.isRefreshing = false
        contentContainer?.gone()
        emptyScreenContainer?.visible()
    }

    private fun showContent() {
        swipeRefreshLayout?.isRefreshing = false
        contentContainer?.visible()
        emptyScreenContainer?.gone()
    }

    private fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    private fun updateAdapter(items: ArrayList<RepoCellModel>) {
        recyclerAdapter.autoNotify(items)
    }

    // endregion

}