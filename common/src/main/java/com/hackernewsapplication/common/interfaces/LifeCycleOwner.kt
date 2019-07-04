package com.hackernewsapplication.common.interfaces

/**
 * @Author rahulravindran
 */
interface LifeCycleOwner<EVENT> {
    fun lifecycle(): LifecycleStreams<EVENT>
}
