package com.boros.android.sample.di.modules

import com.boros.android.sample.core.network.githubApi.GithubApi
import com.boros.android.sample.core.network.githubApi.GithubApiImpl
import dagger.Binds
import dagger.Module

@Module
abstract class ApiModule {

    @Binds
    abstract fun provideGithubApi(githubApi: GithubApiImpl): GithubApi

}