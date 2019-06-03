package com.hackernewsapplication.network.interceptors

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * @Author rahulravindran
 *  Base interceptor class
 */
abstract class BaseInterceptor : Interceptor {

    fun request(chain: Interceptor.Chain?): Request {
        if (chain == null) {
            throw NullPointerException("chain is null")
        }
        return chain.request()
    }

    fun response(chain: Interceptor.Chain?): Response {
        if (chain == null) {
            throw NullPointerException("chain is null")
        }
        return chain.proceed(chain.request())
    }

    fun response(chain: Interceptor.Chain?, request: Request?): Response {
        if (chain == null) {
            throw NullPointerException("chain is null")
        }

        if (request == null) {
            throw NullPointerException("request is null")
        }

        return chain.proceed(request)
    }


}