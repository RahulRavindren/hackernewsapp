package com.hackernewsapplication.common.basecommons

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.hackernewsapplication.common.C
import com.hackernewsapplication.common.entity.LifeCycleEvent
import com.hackernewsapplication.common.interfaces.LifeCycleOwner
import com.hackernewsapplication.common.interfaces.LifecycleStreams
import com.hackernewsapplication.common.utils.Logger

/**
 * @Author rahulravindran
 */
open class BaseFragment : Fragment(),
    LifeCycleOwner<LifeCycleEvent> {
    private lateinit var lifecycleStreams: LifecyleStreams
    protected var isViewVisible: Boolean = false

    override fun lifecycle(): LifecycleStreams<LifeCycleEvent> {
        return lifecycleStreams
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleStreams = LifecyleStreams()
    }

    override fun onStart() {
        Logger.debug(C.LIFE_CYCLE, "fragment start")
        lifecycleStreams.accept(LifeCycleEvent.START)
        super.onStart()
    }

    override fun onResume() {
        Logger.debug(C.LIFE_CYCLE, "fragment resume")
        super.onResume()
        lifecycleStreams.accept(LifeCycleEvent.RESUME)
    }

    override fun onPause() {
        Logger.debug(C.LIFE_CYCLE, "fragment pause")
        super.onPause()
        lifecycleStreams.accept(LifeCycleEvent.START)

    }

    override fun onDestroy() {
        Logger.debug(C.LIFE_CYCLE, "fragment destroy");
        super.onDestroy()
        lifecycleStreams.accept(LifeCycleEvent.DESTROY)

    }

    override fun onStop() {
        super.onStop()
        Logger.debug(C.LIFE_CYCLE, "fragment stop");
        lifecycleStreams.accept(LifeCycleEvent.START)

    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        isViewVisible = isVisibleToUser
        super.setUserVisibleHint(isVisibleToUser)
    }

}