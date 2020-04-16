package com.boros.android.sample.di.modules

import com.boros.android.sample.core.memoryCache.githubRepoCache.GithubRepoCache
import com.boros.android.sample.core.memoryCache.githubRepoCache.GithubRepoCacheImpl
import dagger.Binds
import dagger.Module

@Module
abstract class MemoryCacheModule {

    @Binds
    abstract fun provideGithubRepoMemoryCache(githubRepoMemoryCache: GithubRepoCacheImpl): GithubRepoCache

}