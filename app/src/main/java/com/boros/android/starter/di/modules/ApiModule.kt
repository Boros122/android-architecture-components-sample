package com.boros.android.starter.di.modules

import com.boros.android.starter.core.network.githubApi.GithubApi
import com.boros.android.starter.core.network.githubApi.GithubApiImpl
import dagger.Binds
import dagger.Module

@Module
abstract class ApiModule {

    @Binds
    abstract fun provideGithubApi(githubApi: GithubApiImpl): GithubApi

}