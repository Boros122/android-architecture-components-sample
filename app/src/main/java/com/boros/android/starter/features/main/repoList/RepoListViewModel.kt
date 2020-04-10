package com.boros.android.starter.features.main.repoList

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.boros.android.starter.core.ResponseWrapper
import com.boros.android.starter.core.model.GithubRepo
import com.boros.android.starter.core.repository.RepositoryFactory
import com.boros.android.starter.shared.ui.recyclerView.BaseItemModel

class RepoListViewModel : ViewModel() {

    var githubRepositoriesLiveData: LiveData<ResponseWrapper<ArrayList<GithubRepo>?, String?>>? = null

    fun requestData() {
        if (githubRepositoriesLiveData == null) {
            githubRepositoriesLiveData = RepositoryFactory.getGithubRepository().getGithubRepositories()
        }
    }

    fun createItemModels(repos: ArrayList<GithubRepo>?): ArrayList<BaseItemModel> {
        val itemModels = ArrayList<BaseItemModel>()
        repos?.forEach {
            itemModels.add(createItemModel(it))
        }
        return itemModels
    }

    private fun createItemModel(repo: GithubRepo): BaseItemModel {
        return RepoItemModel(
                repoId = repo.id,
                repoName = repo.name ?: "",
                ownerName = repo.owner?.login,
                ownerImage = repo.owner?.avatarUrl ?: ""
        )
    }

}