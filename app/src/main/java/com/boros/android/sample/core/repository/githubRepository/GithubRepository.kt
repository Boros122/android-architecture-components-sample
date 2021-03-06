package com.boros.android.sample.core.repository.githubRepository

import androidx.lifecycle.LiveData
import com.boros.android.sample.core.model.GithubRepo
import com.boros.android.sample.core.repository.utils.Result
import com.boros.android.sample.core.repository.utils.enums.RequestType

interface GithubRepository {

    fun getGithubRepositoriesLiveData(): LiveData<ArrayList<GithubRepo>>

    suspend fun getGithubRepositories(requestType: RequestType): Result<ArrayList<GithubRepo>>

    suspend fun getGithubRepository(requestType: RequestType, id: Int): Result<GithubRepo>

}