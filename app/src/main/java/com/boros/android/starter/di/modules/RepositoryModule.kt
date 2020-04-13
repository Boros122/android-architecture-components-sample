package com.boros.android.starter.di.modules

import com.boros.android.starter.core.repository.githubRepository.GithubRepository
import com.boros.android.starter.core.repository.githubRepository.GithubRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideGithubRepository(githubRepository: GithubRepositoryImpl): GithubRepository

}