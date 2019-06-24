package com.hackernewsapplication.common.basecommons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.hackernewsapplication.common.R
import com.hackernewsapplication.common.entity.LifeCycleEvent
import com.hackernewsapplication.common.interfaces.BaseView
import com.hackernewsapplication.common.interfaces.LifeCycleOwner
import com.hackernewsapplication.common.interfaces.LifecycleStreams
import com.hackernewsapplication.common.utils.Utils
import com.hackernewsapplication.common.utils.showUserFeedback
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.base_activity_layout.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * @Author rahulravindran
 */
open class BaseActivity : AppCompatActivity(), BaseView, LifeCycleOwner<LifeCycleEvent>, Consumer<Utils.Event> {
    private lateinit var lifecycleStream: LifecyleStreams

    override fun accept(t: Utils.Event?) {
        val message = t?.message
        if (message.equals(Utils.getString(R.string.no_network_msg))) {
            window.decorView.showUserFeedback(message ?: "")
        }
    }

    override fun lifecycle(): LifecycleStreams<LifeCycleEvent> {
        return lifecycleStream
    }

    override fun onStart() {
        super.onStart()
        lifecycleStream.accept(LifeCycleEvent.START)
    }

    override fun onPause() {
        super.onPause()
        lifecycleStream.accept(LifeCycleEvent.PAUSE)
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleStream.accept(LifeCycleEvent.DESTROY)
    }

    override fun onStop() {
        super.onStop()
        lifecycleStream.accept(LifeCycleEvent.STOP)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleStream = LifecyleStreams()
        Utils.eventSubject.subscribe(this)
        super.setContentView(R.layout.base_activity_layout)
    }

    override fun setContentView(layoutResID: Int) {
        if (layoutResID != View.NO_ID) {
            val infalter = LayoutInflater.from(baseContext)
                .inflate(layoutResID, base_view_container, false)
            base_view_container.removeAllViews()
            base_view_container.addView(infalter)
        }

    }

    protected fun initToolbar(status: Boolean) {
        if (status) {
            setSupportActionBar(app_toolbar)
            val supportActionBar = actionBar
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeButtonEnabled(true)
            supportActionBar?.title = "Carousel News"
            app_toolbar.visibility = View.VISIBLE
        }
    }

    override fun setContentView(view: View?) {
        base_view_container.removeView(view)
        base_view_container.addView(view)
    }

    override fun showProgress(state: Boolean) {
        base_view_container.visibility = if (state) View.GONE else View.VISIBLE
        base_view_progress.visibility = if (state) View.VISIBLE else View.GONE

    }

    override fun showErrorPage(state: Boolean) {
        base_view_container.visibility = if (state) View.GONE else View.VISIBLE
        base_view_progress.visibility = if (state) View.GONE else View.VISIBLE
        base_view_error_layout_container.visibility = if (state) View.VISIBLE else View.GONE
    }

    protected fun getErrorContainer(): ViewGroup {
        return base_view_error_layout_container
    }

}