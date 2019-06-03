package com.hackernewsapplication.network

import android.content.Context
import okhttp3.Interceptor
import okhttp3.OkHttpClient

class NetworkSDK {

    companion object {
        var mContext: Context? = null
            private set

        lateinit var client: OkHttpClient
            private set

        private var interceptorList: List<out Interceptor> = emptyList()

        // init network SDK
        @JvmStatic
        fun init(context: Context?) {
            //no global interceptor
            if (context == null) {
                throw NullPointerException("context is null")
            }
            mContext = context
            createClient(interceptorList)
        }


        // init network SDK
        @JvmStatic
        fun init(context: Context?, vararg interceptor: Interceptor) {
            if (context == null) {
                throw NullPointerException("context is null")
            }

            mContext = context
            interceptorList = interceptor?.asList()
            createClient(interceptorList)
        }

        @JvmStatic
        fun init(context: Context?, interceptor: List<Interceptor>) {
            if (context == null) {
                throw NullPointerException("context is null")
            }

            mContext = context
            interceptorList = interceptor
            createClient(interceptorList)
        }


        //create client
        private fun createClient(interceptors: Collection<Interceptor>) {
            client =
                RetrofitAdapter.getClient(interceptors)
        }

    }

}