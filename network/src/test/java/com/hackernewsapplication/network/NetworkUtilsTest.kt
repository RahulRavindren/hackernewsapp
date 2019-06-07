package com.hackernewsapplication.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.hackernewsapplication.network.utils.NetworkUtils
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows
import org.robolectric.shadows.ShadowConnectivityManager
import org.robolectric.shadows.ShadowNetworkInfo

/**
 * @Author rahulravindran
 */
@RunWith(RobolectricTestRunner::class)
class NetworkUtilsTest {

    lateinit var shadowCM: ShadowConnectivityManager

    @Before
    fun setUp() {
        NetworkSDK.init(RuntimeEnvironment.systemContext)
        shadowCM = Shadows.shadowOf(getConnectivityManager())
        val shadowactiveNetwork = Shadows.shadowOf(getConnectivityManager().activeNetworkInfo)
    }


    @Test
    fun `is network connected test`() {
        val networkInfo = ShadowNetworkInfo.newInstance(
            NetworkInfo.DetailedState.CONNECTED,
            ConnectivityManager.TYPE_WIFI,
            0,
            true,
            true
        )
        shadowCM.setActiveNetworkInfo(networkInfo)
        val activeInfo = getConnectivityManager().activeNetworkInfo
        Assert.assertTrue(NetworkUtils.isNetworkConnected())
    }

    @Test
    fun `is network disconnected test`() {
        val networkInfo = ShadowNetworkInfo.newInstance(
            NetworkInfo.DetailedState.CONNECTED,
            ConnectivityManager.TYPE_WIFI,
            0,
            true,
            false
        )
        shadowCM.setActiveNetworkInfo(networkInfo)
        val activeInfo = getConnectivityManager().activeNetworkInfo
        Assert.assertFalse(NetworkUtils.isNetworkConnected())
    }

    fun getConnectivityManager(): ConnectivityManager =
        RuntimeEnvironment.application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
}