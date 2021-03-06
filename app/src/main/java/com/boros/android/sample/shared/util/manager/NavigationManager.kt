package com.boros.android.sample.shared.util.manager

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.boros.android.sample.R
import com.boros.android.sample.features.main.repoList.RepoListFragmentDirections
import com.boros.android.sample.shared.sharedPreferences.SharedPreferencesManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationManager @Inject constructor(private val sharedPreferencesManager: SharedPreferencesManager) {

    fun updateStartDestinationIfNeeded(navController: NavController) {
        if (sharedPreferencesManager.settings.isIntroPassed && navController.currentDestination?.id == R.id.introFragment) {
            navigateToRepoList(navController, true)
        }
    }

    fun navigateToRepoList(navController: NavController, needToClearStack: Boolean = false) {
        if (needToClearStack) {
            navController.graph.startDestination = R.id.repoListFragment
            navController.navigate(R.id.repoListFragment, null, NavOptions.Builder().setPopUpTo(R.id.introFragment, true).build())
        } else {
            navController.navigate(R.id.repoListFragment)
        }
    }

    fun navigateToRepoDetail(navController: NavController, id: Int) {
        val action = RepoListFragmentDirections.actionRepoListFragmentToRepoDetailFragment(id)
        navController.navigate(action)
    }

    fun navigateToSettings(navController: NavController) {
        navController.navigate(R.id.settingsFragment)
    }

}