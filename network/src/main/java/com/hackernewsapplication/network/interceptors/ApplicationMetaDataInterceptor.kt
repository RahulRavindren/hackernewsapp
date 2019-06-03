package com.hackernewsapplication.network.interceptors

import com.hackernewsapplication.common.utils.AppConfigBuilder
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @Author rahulravindran
 *  Interceptor class for adding application metadata as header to every request
 *  APPLICATION_VERSION_NAME : version name of application
 *  APPLICATION_ID : application id
 *  APPLICATION_VERSION_CODE : version code of the application
 */
class ApplicationMetaDataInterceptor : BaseInterceptor() {

    override fun intercept(chain: Interceptor.Chain?): Response {
        val request = request(chain)
        val requestBuilder = request.newBuilder()
            .addHeader(
                "APPLICATION_VERSION_NAME",
                AppConfigBuilder.getInstance()?.applicationVersion ?: ""
            )
            .addHeader(
                "APPLICATION_ID",
                AppConfigBuilder.getInstance()?.applicationId ?: ""
            )
            .addHeader(
                "APPLICATION_VERISON_CODE",
                AppConfigBuilder.getInstance()?.applicationCode.toString()
            )
        return response(chain, requestBuilder.build())
    }
}