package com.boros.android.starter.di.components

import androidx.fragment.app.FragmentManager
import com.boros.android.starter.di.modules.MainModule
import com.boros.android.starter.di.scopes.ActivityScope
import com.boros.android.starter.features.intro.IntroFragment
import com.boros.android.starter.features.main.MainActivity
import com.boros.android.starter.features.main.repoDetail.RepoDetailFragment
import com.boros.android.starter.features.main.repoList.RepoListFragment
import com.boros.android.starter.features.main.settings.SettingsFragment
import com.boros.android.starter.features.splash.SplashScreenActivity
import com.boros.android.starter.shared.ui.activity.BaseActivity
import com.boros.android.starter.shared.ui.fragment.BaseFragment
import dagger.BindsInstance
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [MainModule::class])
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance supportFragmentManager: FragmentManager): MainComponent
    }

    fun inject(activity: BaseActivity)
    fun inject(activity: BaseFragment)

    fun inject(activity: SplashScreenActivity)
    fun inject(fragment: IntroFragment)

    fun inject(activity: MainActivity)
    fun inject(fragment: RepoDetailFragment)
    fun inject(fragment: RepoListFragment)
    fun inject(fragment: SettingsFragment)

}