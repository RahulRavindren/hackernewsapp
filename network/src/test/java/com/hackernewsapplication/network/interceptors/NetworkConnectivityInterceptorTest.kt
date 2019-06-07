package com.hackernewsapplication.network.interceptors

import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.hackernewsapplication.common.utils.AppConfigBuilder
import com.hackernewsapplication.network.RetrofitAdapter
import com.hackernewsapplication.network.exceptions.NetworkConnectivityException
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowConnectivityManager
import org.robolectric.shadows.ShadowNetworkInfo

@RunWith(RobolectricTestRunner::class)
class NetworkConnectivityInterceptorTest : BaseInterceptorTest() {
    lateinit var service: ApplicationMetaDataInterceptorTest.TestService

    @Before
    fun setUp() {
        init()
        AppConfigBuilder.Builder()
            .setApplicationCode(1)
            .setApplicationID("com.hackernewsapplication.android")
            .setApplicationEnv("dev")
            .setApplicationVersion("1.0").build()

        val shadowCM = ShadowConnectivityManager()
        shadowCM.setNetworkInfo(
            ConnectivityManager.TYPE_MOBILE,
            ShadowNetworkInfo.newInstance(
                NetworkInfo.DetailedState.DISCONNECTED,
                ConnectivityManager.TYPE_MOBILE,
                ConnectivityManager.TYPE_MOBILE_MMS,
                true,
                false
            )
        );
    }

    @Test(expected = NetworkConnectivityException::class)
    fun `network connectivity set to false`() {
        setupMockServer()
        service = RetrofitAdapter
            .Factory().getRestService(
                ApplicationMetaDataInterceptorTest.TestService::class.java, server.url("/"),
                listOf(ApplicationMetaDataInterceptor())
            )

        //executing dummy request
        service.makeRequest()

    }


}