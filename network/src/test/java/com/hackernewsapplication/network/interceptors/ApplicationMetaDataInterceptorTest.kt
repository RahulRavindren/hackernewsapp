package com.hackernewsapplication.network.interceptors

import com.hackernewsapplication.common.utils.AppConfigBuilder
import com.hackernewsapplication.network.RetrofitAdapter
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

@RunWith(MockitoJUnitRunner::class)
class ApplicationMetaDataInterceptorTest : BaseInterceptorTest() {
    lateinit var service: TestService

    @Before
    fun setUp() {
        init()
        AppConfigBuilder.getInstance(
            AppConfigBuilder.Builder()
                .setApplicationCode(1)
                .setApplicationEnv("dev")
                .setApplicationID("com.hackernewsapplication.android")
                .setApplicationVersion("1.0")
        )
    }

    @Test
    fun `test application meta interceptor`() {
        setupMockServer()
        service = RetrofitAdapter.Factory().getRestService(
            TestService::class.java, server.url("/test/"),
                listOf(ApplicationMetaDataInterceptor())
            )

        //executing dummy request
        service.makeRequest().execute()
        val recordedRequest = server.takeRequest()
        Assert.assertEquals("1", recordedRequest.getHeader("APPLICATION_VERISON_CODE"))
        Assert.assertEquals("1.0", recordedRequest.getHeader("APPLICATION_VERSION_NAME"))
        Assert.assertEquals("com.hackernewsapplication.android", recordedRequest.getHeader("APPLICATION_ID"))
    }

    interface TestService {
        @GET("/")
        fun makeRequest(): Call<Response<Any>>
    }
}