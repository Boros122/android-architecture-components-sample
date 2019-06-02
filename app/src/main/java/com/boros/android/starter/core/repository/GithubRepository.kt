package com.boros.android.starter.core.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.boros.android.starter.core.ResponseWrapper
import com.boros.android.starter.core.database.helper.GithubRepoDBHelper
import com.boros.android.starter.core.model.GithubRepo
import com.boros.android.starter.core.network.APIDefinition
import com.boros.android.starter.core.utils.message
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubRepository(private val apiDefinition: APIDefinition, private val generalErrorMessage: String) {

    private val githubRepoLiveData: MutableLiveData<ResponseWrapper<ArrayList<GithubRepo>?, String?>> = MutableLiveData()

    fun getGithubRepositories(): LiveData<ResponseWrapper<ArrayList<GithubRepo>?, String?>> {
        githubRepoLiveData.value?.error = null
        GithubRepoDBHelper.getGithubRepos {
            if (!it.isNullOrEmpty() && githubRepoLiveData.value == null) {
                githubRepoLiveData.value = ResponseWrapper(it, null)
            }
        }
        apiDefinition.getRepositories().enqueue(object : Callback<ArrayList<GithubRepo>> {
            override fun onResponse(call: Call<ArrayList<GithubRepo>>, response: Response<ArrayList<GithubRepo>>) {
                if (response.code() in 200..300) {
                    val result = response.body()
                    if (githubRepoLiveData.value?.data != result) {
                        githubRepoLiveData.value = ResponseWrapper(result, null)
                    }
                    GithubRepoDBHelper.saveGithubRepoList(result)
                } else {
                    githubRepoLiveData.value = ResponseWrapper(null, response.errorBody().message(generalErrorMessage))
                }
            }

            override fun onFailure(call: Call<ArrayList<GithubRepo>>, t: Throwable) {
                githubRepoLiveData.value = ResponseWrapper(null, generalErrorMessage)
            }
        })
        return githubRepoLiveData
    }

    fun getGithubRepository(id: Int): LiveData<ResponseWrapper<GithubRepo?, String?>> {
        val githubRepo = githubRepoLiveData.value?.data?.find { it.id == id }
        val githubRepoLiveData: MutableLiveData<ResponseWrapper<GithubRepo?, String?>> = MutableLiveData()
        githubRepoLiveData.value = ResponseWrapper(githubRepo, null)
        return githubRepoLiveData
    }

}