package com.boros.android.sample.di.modules

import com.boros.android.sample.core.repository.githubRepository.GithubRepository
import com.boros.android.sample.core.repository.githubRepository.GithubRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideGithubRepository(githubRepository: GithubRepositoryImpl): GithubRepository

}