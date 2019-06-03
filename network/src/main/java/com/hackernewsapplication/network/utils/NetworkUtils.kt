package com.hackernewsapplication.network.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import com.hackernewsapplication.network.NetworkSDK

/**
 * @Author rahulravindran
 * Network related Util object
 */
object NetworkUtils {
    //network conectivity check util method
    @SuppressLint("MissingPermission")
    fun isNetworkConnected(): Boolean {
        val connectionManager =
            NetworkSDK.mContext?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectionManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }


}