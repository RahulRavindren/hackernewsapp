package com.hackernewsapplication.android

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import com.hackernewsapplication.android.utils.AppNavigation
import com.hackernewsapplication.common.C
import com.hackernewsapplication.common.basecommons.BaseActivity
import com.hackernewsapplication.common.utils.Logger
import kotlinx.android.synthetic.main.activity_main.*


class NewsHomeActivity : BaseActivity(), AppNavigation.NavigationChangeListener {
    private val TAG = NewsHomeActivity::class.java.simpleName
    private var appNavigation: AppNavigation? = null

    fun getNavigation(): AppNavigation? = appNavigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initToolbar(true)
        setContentView(R.layout.activity_main)
        if (appNavigation == null) {
            appNavigation = AppNavigation(supportFragmentManager, nav_host)
        }

        if (savedInstanceState == null) {
            appNavigation?.init(null)
        }
    }

    override fun onChangeDestination(controller: NavController, destination: NavDestination, arguments: Bundle?) {
        Logger.debug(TAG, "destination :: ${destination.navigatorName} args :: ${arguments.toString()}")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        val bundle = appNavigation?.storeState()
        outState.putBundle(C.NAV_STATE, bundle)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        appNavigation?.restoreState(savedInstanceState?.getBundle(C.NAV_STATE))
        super.onRestoreInstanceState(savedInstanceState)
    }


}
