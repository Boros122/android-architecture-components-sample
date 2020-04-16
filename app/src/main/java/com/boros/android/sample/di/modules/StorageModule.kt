package com.boros.android.sample.di.modules

import com.boros.android.sample.core.localStorage.storage.githubRepoStorage.GithubRepoStorage
import com.boros.android.sample.core.localStorage.storage.githubRepoStorage.GithubRepoStorageImpl
import com.boros.android.sample.core.localStorage.storage.sessionStorage.SessionStorage
import com.boros.android.sample.core.localStorage.storage.sessionStorage.SessionStorageImpl
import dagger.Binds
import dagger.Module

@Module
abstract class StorageModule {

    @Binds
    abstract fun provideGithubStorage(githubRepoStorage: GithubRepoStorageImpl): GithubRepoStorage

    @Binds
    abstract fun provideSessionStorage(sessionStorage: SessionStorageImpl): SessionStorage

}