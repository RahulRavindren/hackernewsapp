package com.hackernewsapplication.common.utils

import android.app.Activity
import android.app.Application
import android.os.Bundle

/**
 * @Author rahulravindran
 */
class ActivityLifeCycleLogger(private val debug: Boolean) : Application.ActivityLifecycleCallbacks {


    override fun onActivityPaused(p0: Activity?) {
        if (debug) {
            Logger.info(p0?.localClassName, "Activity paused")
        }
    }

    override fun onActivityResumed(p0: Activity?) {
        if (debug) {
            Logger.info(p0?.localClassName, "Activity resume")
        }

    }

    override fun onActivityStarted(p0: Activity?) {

        if (debug) {
            Logger.info(p0?.localClassName, "Activity start")
        }
    }

    override fun onActivityDestroyed(p0: Activity?) {

        if (debug) {
            Logger.info(p0?.localClassName, "Activity destroy")
        }
    }

    override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) {

        if (debug) {
            Logger.info(
                p0?.localClassName,
                "Activity save instance state"
            )
        }
    }

    override fun onActivityStopped(p0: Activity?) {
        if (debug) {
            Logger.info(p0?.localClassName, "Activity stopped")
        }
    }

    override fun onActivityCreated(p0: Activity?, p1: Bundle?) {
        if (debug) {
            Logger.info(p0?.localClassName, "Activity created")
        }
    }
}
