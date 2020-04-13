package com.boros.android.starter.features.main.repoList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boros.android.starter.core.model.GithubRepo
import com.boros.android.starter.core.repository.githubRepository.GithubRepository
import com.boros.android.starter.core.repository.utils.Result
import com.boros.android.starter.core.repository.utils.enums.RequestType
import kotlinx.coroutines.launch
import javax.inject.Inject

class RepoListViewModel @Inject constructor(
        private val githubRepository: GithubRepository
) : ViewModel() {

    val loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()

    val errorLiveData: MutableLiveData<String> = MutableLiveData()

    val cellModelLiveData: MutableLiveData<ArrayList<RepoCellModel>> = MutableLiveData()

    fun fetchData() {
        viewModelScope.launch {
            loadingLiveData.value = true
            githubRepository.getGithubRepositories(RequestType.NETWORK).let { result ->
                loadingLiveData.value = false
                if (result is Result.Success) {
                    cellModelLiveData.value = createCellModels(result.data)
                } else if (result is Result.Error) {
                    errorLiveData.value = result.error.message
                }
            }
        }
    }

    fun isDataAvailable() = cellModelLiveData.value != null

    private fun createCellModels(repos: ArrayList<GithubRepo>?): ArrayList<RepoCellModel> {
        val itemModels = ArrayList<RepoCellModel>()
        repos?.forEach {
            itemModels.add(createCellModel(it))
        }
        return itemModels
    }

    private fun createCellModel(repo: GithubRepo): RepoCellModel {
        return RepoCellModel(
                cellId = repo.id.toString(),
                repoId = repo.id,
                repoName = repo.name ?: "",
                ownerName = repo.owner?.login,
                ownerImage = repo.owner?.avatarUrl ?: ""
        )
    }

}