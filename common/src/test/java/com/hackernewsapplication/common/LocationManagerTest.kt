package com.hackernewsapplication.common

import android.content.Context
import android.os.Looper
import com.hackernewsapplication.common.utils.LocationManager
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LocationManagerTest {
    @Mock
    lateinit var looper: Looper

    @Mock
    private lateinit var context: Context

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test(expected = NullPointerException::class)
    fun `nullContextTest`() {
        LocationManager.performActionOnLastLocation(null) {
            // do nothing
        }
    }


}