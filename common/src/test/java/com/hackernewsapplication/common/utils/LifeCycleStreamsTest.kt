package com.hackernewsapplication.common.utils

import com.hackernewsapplication.common.basecommons.LifecyleStreams
import com.hackernewsapplication.common.entity.LifeCycleEvent
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.junit.internal.runners.JUnit4ClassRunner
import org.junit.runner.RunWith

@RunWith(JUnit4ClassRunner::class)
class LifeCycleStreamsTest {
    lateinit var lifecycle: LifecyleStreams
    var testObserver: TestObserver<LifeCycleEvent>? = TestObserver()

    @Before
    fun setUp() {
        lifecycle = LifecyleStreams()
        testObserver?.let { lifecycle.events().subscribe(it) }
    }

    @Test
    fun `test onStart`() {
        lifecycle.accept(LifeCycleEvent.START)
        testObserver?.assertValue(LifeCycleEvent.START)
    }

    @Test
    fun `test onResume`() {
        lifecycle.accept(LifeCycleEvent.RESUME)
        testObserver?.assertValue(LifeCycleEvent.RESUME)
    }

    @Test
    fun `test onPause`() {
        lifecycle.accept(LifeCycleEvent.PAUSE)
        testObserver?.assertValue(LifeCycleEvent.PAUSE)
    }

    @Test
    fun `test onDestory`() {
        lifecycle.accept(LifeCycleEvent.DESTROY)
        testObserver?.assertValue(LifeCycleEvent.DESTROY)
    }

}
