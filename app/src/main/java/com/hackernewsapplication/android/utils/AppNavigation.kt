package com.hackernewsapplication.android.utils

import android.os.Bundle
import android.widget.FrameLayout
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import com.hackernewsapplication.android.R

class AppNavigation(private val supportFragmentManager: FragmentManager, private val container: FrameLayout) {

    private val hostFragment: NavHostFragment = NavHostFragment.create(R.navigation.news_nav_graph)
    private var navController: NavController? = null
    private var listener: NavigationChangeListener? = null

    private val destinationChangeListener =
        NavController.OnDestinationChangedListener { controller, destination, arguments ->
            listener?.onChangeDestination(controller, destination, arguments)
        }

    fun getCurrentVisibleFragment(): Fragment? = hostFragment.childFragmentManager.primaryNavigationFragment


    fun navigate(@IdRes navId: Int) {
        hostFragment.navController.navigate(navId)
    }

    fun navigate(@IdRes navId: Int, bundle: Bundle?) {
        hostFragment.navController.navigate(navId, bundle)
    }

    fun init(bundle: Bundle?) {
        if (bundle == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(container.id, hostFragment)
                .setPrimaryNavigationFragment(hostFragment)
                .commitNow()
            hostFragment.navController.addOnDestinationChangedListener(destinationChangeListener)
        } else {
            hostFragment.navController.restoreState(bundle)
        }
    }

    fun restoreState(bundle: Bundle?) {
        navController?.restoreState(bundle)
    }

    fun storeState(): Bundle? = hostFragment.navController.saveState()


    fun navController(): NavController? = navController

    fun setNavigationChangeListener(listener: NavigationChangeListener) {
        this.listener = listener
    }

    interface NavigationChangeListener {
        fun onChangeDestination(
            controller: NavController,
            destination: NavDestination, arguments: Bundle?
        )
    }

}
