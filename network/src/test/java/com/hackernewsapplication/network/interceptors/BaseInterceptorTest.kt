package com.hackernewsapplication.network.interceptors

import android.content.Context
import com.hackernewsapplication.network.NetworkSDK
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
open class BaseInterceptorTest {
    @Mock
    lateinit var context: Context
    lateinit var server: MockWebServer

    fun init() {
        MockitoAnnotations.initMocks(this)
        NetworkSDK.init(context)
    }

    fun setupMockServer() {
        server = MockWebServer()
        server.start()
        server.enqueue(MockResponse())

    }
}