package com.hackernewsapplication.android

import android.os.Bundle
import com.hackernewsapplication.android.utils.AppNavigation
import com.hackernewsapplication.common.basecommons.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*


class NewsHomeActivity : BaseActivity() {
    private var appNavigation: AppNavigation? = null

    fun getNavigation(): AppNavigation? = appNavigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar(true)
        appNavigation = AppNavigation(supportFragmentManager, nav_host)
    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        appNavigation?.restoreState(savedInstanceState)
        super.onRestoreInstanceState(savedInstanceState)
    }


}
