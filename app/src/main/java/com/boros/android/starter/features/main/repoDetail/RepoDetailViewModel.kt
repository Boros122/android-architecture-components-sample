package com.boros.android.starter.features.main.repoDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.boros.android.starter.core.ResponseWrapper
import com.boros.android.starter.core.model.GithubRepo
import com.boros.android.starter.core.repository.RepositoryFactory

class RepoDetailViewModel : ViewModel() {

    var githubRepositoryLiveData: LiveData<ResponseWrapper<GithubRepo?, String?>>? = null

    var repoId: Int = 0

    fun requestData() {
        if (githubRepositoryLiveData == null) {
            githubRepositoryLiveData = RepositoryFactory.getGithubRepository().getGithubRepository(repoId)
        }
    }

}