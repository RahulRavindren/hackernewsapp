package com.hackernewsapplication.network.interceptors

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.hackernewsapplication.common.utils.AppConfigBuilder
import com.hackernewsapplication.network.RetrofitAdapter
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows
import org.robolectric.shadows.ShadowConnectivityManager
import org.robolectric.shadows.ShadowNetworkInfo

@RunWith(RobolectricTestRunner::class)
class NetworkConnectivityInterceptorTest : BaseInterceptorTest() {
    lateinit var service: ApplicationMetaDataInterceptorTest.TestService
    lateinit var shadowCM: ShadowConnectivityManager

    @Before
    fun setUp() {
        init()
        AppConfigBuilder.Builder()
            .setApplicationCode(1)
            .setApplicationID("com.hackernewsapplication.android")
            .setApplicationEnv("dev")
            .setApplicationVersion("1.0").build()

        shadowCM = Shadows.shadowOf(getConnectivityManager())
    }

    @Test
    fun `network connectivity set to false`() {
        val networkInfo = ShadowNetworkInfo.newInstance(
            NetworkInfo.DetailedState.CONNECTED,
            ConnectivityManager.TYPE_WIFI,
            0,
            true,
            false
        )
        shadowCM.setActiveNetworkInfo(networkInfo)

        setupMockServer()
        service = RetrofitAdapter.Factory().getRestService(
            ApplicationMetaDataInterceptorTest.TestService::class.java, server.url("/"), listOf()
        )
        //executing dummy request
        service.makeRequest()

    }

    fun getConnectivityManager(): ConnectivityManager =
        RuntimeEnvironment.application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

}