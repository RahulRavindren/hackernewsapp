package com.hackernewsapplication.common.basecommons

import com.hackernewsapplication.common.entity.LifeCycleEvent
import com.hackernewsapplication.common.interfaces.LifecycleStreams
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * @Author rahulravindran
 */
class LifecyleStreams :
    LifecycleStreams<LifeCycleEvent> {
    private val events = PublishSubject.create<LifeCycleEvent>()

    fun accept(event: LifeCycleEvent) {
        events.onNext(event)
    }

    override fun events(): Observable<LifeCycleEvent> {
        return events
    }

    override fun onStart(): Observable<LifeCycleEvent> {
        return events.filter { t -> t == LifeCycleEvent.START }
    }

    override fun onResume(): Observable<LifeCycleEvent> {
        return events.filter { t -> t == LifeCycleEvent.RESUME }
    }

    override fun onPause(): Observable<LifeCycleEvent> {
        return events.filter { t -> t == LifeCycleEvent.PAUSE }
    }

    override fun onStop(): Observable<LifeCycleEvent> {
        return events.filter { t -> t == LifeCycleEvent.STOP }
    }

    override fun onDestroy(): Observable<LifeCycleEvent> {
        return events.filter { t -> t == LifeCycleEvent.DESTROY }
    }
}