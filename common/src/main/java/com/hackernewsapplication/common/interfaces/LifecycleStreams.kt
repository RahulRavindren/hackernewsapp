package com.hackernewsapplication.common.interfaces

import androidx.annotation.CheckResult
import io.reactivex.BackpressureStrategy
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

/**
 * @Author rahulravindran
 */
interface LifecycleStreams<EVENT> {
    @CheckResult
    fun events(): Observable<EVENT>

    fun onStart(): Observable<EVENT>
    fun onResume(): Observable<EVENT>
    fun onPause(): Observable<EVENT>
    fun onStop(): Observable<EVENT>
    fun onDestroy(): Observable<EVENT>

    fun onStopFlowable(): Flowable<EVENT> {
        return onStop().toFlowable(BackpressureStrategy.LATEST)
    }

    fun onDestroyFlowable(): Flowable<EVENT> {
        return onDestroy().toFlowable(BackpressureStrategy.LATEST)
    }

    fun onDestroyCompletable(): Completable {
        return onDestroy().ignoreElements()
    }


}