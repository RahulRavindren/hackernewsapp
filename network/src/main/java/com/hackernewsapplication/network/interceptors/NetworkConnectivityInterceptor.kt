package com.hackernewsapplication.network.interceptors

import com.hackernewsapplication.common.utils.Utils
import com.hackernewsapplication.network.NetworkSDK
import com.hackernewsapplication.network.R
import com.hackernewsapplication.network.utils.NetworkUtils
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @Author rahulravindran
 * Interceptor class that checks network state during request call
 */
class NetworkConnectivityInterceptor : BaseInterceptor() {

    override fun intercept(chain: Interceptor.Chain?): Response {
        if (!NetworkUtils.isNetworkConnected()) {
            Utils.eventSubject.onNext(
                Utils.Event(
                    NetworkSDK.mContext?.getString(R.string.no_network_msg),
                    Throwable(NetworkSDK.mContext?.getString(R.string.no_network_msg))
                )
            )
        }
        return response(chain)
    }

}