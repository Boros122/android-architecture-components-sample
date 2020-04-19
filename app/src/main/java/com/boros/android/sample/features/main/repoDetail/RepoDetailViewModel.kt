package com.boros.android.sample.features.main.repoDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boros.android.sample.core.model.GithubRepo
import com.boros.android.sample.core.repository.githubRepository.GithubRepository
import com.boros.android.sample.core.repository.utils.Result
import com.boros.android.sample.core.repository.utils.enums.RequestType
import kotlinx.coroutines.launch
import javax.inject.Inject

class RepoDetailViewModel @Inject constructor(
        private val githubRepository: GithubRepository
) : ViewModel() {

    var repoId: Int = 0

    val errorLiveData: MutableLiveData<String> = MutableLiveData()

    val repoDetailScreenModelLiveData: MutableLiveData<RepoDetailScreenModel> = MutableLiveData()

    fun requestData() {
        viewModelScope.launch {
            githubRepository.getGithubRepository(RequestType.MEMORY, repoId).let { result ->
                if (result is Result.Success) {
                    repoDetailScreenModelLiveData.value = createRepoDetailScreenModel(result.data)
                } else if (result is Result.Error) {
                    errorLiveData.value = result.error.message
                }
            }

        }
    }

    private fun createRepoDetailScreenModel(githubRepo: GithubRepo): RepoDetailScreenModel {
        return RepoDetailScreenModel(
                avatarUrl = githubRepo.owner?.avatarUrl ?: "",
                repoName = githubRepo.name ?: "",
                ownerName = githubRepo.owner?.login,
                description = githubRepo.description
        )
    }

}