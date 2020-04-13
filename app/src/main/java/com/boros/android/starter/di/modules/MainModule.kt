package com.boros.android.starter.di.modules

import androidx.lifecycle.ViewModel
import com.boros.android.starter.di.ViewModelKey
import com.boros.android.starter.features.intro.IntroViewModel
import com.boros.android.starter.features.main.MainViewModel
import com.boros.android.starter.features.main.repoDetail.RepoDetailViewModel
import com.boros.android.starter.features.main.repoList.RepoListViewModel
import com.boros.android.starter.features.main.settings.SettingsViewModel
import com.boros.android.starter.features.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(IntroViewModel::class)
    abstract fun bindIntroViewModel(viewModel: IntroViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RepoDetailViewModel::class)
    abstract fun bindRepoDetailViewModel(viewModel: RepoDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RepoListViewModel::class)
    abstract fun bindRepoListViewModel(viewModel: RepoListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    abstract fun bindSettingsViewModel(viewModel: SettingsViewModel): ViewModel

}