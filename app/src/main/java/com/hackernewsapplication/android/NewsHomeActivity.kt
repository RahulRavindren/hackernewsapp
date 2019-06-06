package com.hackernewsapplication.android

import android.os.Bundle
import com.hackernewsapplication.android.utils.AppNavigation
import com.hackernewsapplication.common.C
import com.hackernewsapplication.common.basecommons.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*


class NewsHomeActivity : BaseActivity() {
    private var appNavigation: AppNavigation? = null

    fun getNavigation(): AppNavigation? = appNavigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar(true)

        if (appNavigation == null) {
            appNavigation = AppNavigation(supportFragmentManager, nav_host)
            appNavigation?.restoreState(savedInstanceState?.getBundle(C.NAV_STATE))
        }
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
