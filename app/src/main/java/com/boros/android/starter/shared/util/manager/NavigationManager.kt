package com.boros.android.starter.shared.util.manager

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.boros.android.starter.R
import com.boros.android.starter.features.main.repoList.RepoListFragmentDirections
import com.boros.android.starter.shared.sharedPreferences.SharedPreferencesManager

object NavigationManager {

    fun updateStartDestinationIfNeeded(navController: NavController) {
        if (SharedPreferencesManager.settings.isIntroPassed && navController.currentDestination?.id == R.id.introFragment) {
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