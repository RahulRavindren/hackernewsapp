package com.hackernewsapplication.android.utils

import android.os.Bundle
import android.widget.FrameLayout
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.hackernewsapplication.android.R

class AppNavigation(supportFragmentManager: FragmentManager, container: FrameLayout) {

    private val hostFragment: NavHostFragment = NavHostFragment.create(R.navigation.news_nav_graph)
    private val navController: NavController

    private val destinationChangeListener =
        NavController.OnDestinationChangedListener { _, _, _ -> }

    fun getCurrentVisibleFragment(): Fragment? = hostFragment.childFragmentManager.primaryNavigationFragment


    fun navigate(@IdRes navId: Int) {
        navController.navigate(navId)
    }

    fun navigate(@IdRes navId: Int, bundle: Bundle?) {
        navController.navigate(navId, bundle)
    }

    init {
        supportFragmentManager
            .beginTransaction()
            .replace(container.id, hostFragment)
            .setPrimaryNavigationFragment(hostFragment)
            .commitNow()
        navController = hostFragment.navController
        navController.addOnDestinationChangedListener(destinationChangeListener)
    }


    fun restoreState(bundle: Bundle?) {
        navController.restoreState(bundle)
    }

    fun storeState() {
        navController.saveState()
    }

}