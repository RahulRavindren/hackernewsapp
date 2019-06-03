package com.hackernewsapplication.network.interceptors

import android.content.Context
import com.hackernewsapplication.network.NetworkSDK
import com.hackernewsapplication.network.utils.NetworkUtils
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * @Author rahulravindran
 */
@RunWith(MockitoJUnitRunner::class)
class NetworkUtilsTest {

    @Mock
    lateinit var context: Context

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        NetworkSDK.init(context)
    }


    @Test
    fun `is network connected test`() {
        Assert.assertTrue(NetworkUtils.isNetworkConnected())
    }
}