package com.hackernewsapplication.android.utils

import androidx.test.espresso.IdlingResource
import java.util.concurrent.atomic.AtomicBoolean

class SimpleIdlingResource : IdlingResource {

    private val isIdleNow = AtomicBoolean(true)
    private var resourceCallback: IdlingResource.ResourceCallback? = null


    override fun getName(): String = this.name

    override fun isIdleNow(): Boolean = isIdleNow.get()

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this.resourceCallback = callback
    }

    fun setIdleState(state: Boolean) {
        isIdleNow.set(state)
        if (state && resourceCallback != null) {
            resourceCallback?.onTransitionToIdle()
        }
    }
}