package com.hackernewsapplication.network

import android.annotation.SuppressLint
import com.hackernewsapplication.common.utils.ApplicationUrlContainer
import com.hackernewsapplication.network.interceptors.ApplicationMetaDataInterceptor
import com.hackernewsapplication.network.interceptors.NetworkConnectivityInterceptor
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import surveyapp.com.network.BuildConfig
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

/**
 * @Author rahulravindran
 */

const val READ_TIME_OUT = 60000L
const val WRITE_TIME_OUT = 60000L

class RetrofitAdapter {

    companion object {

        /*
        * build client
        * @param baseHost   Base api end point
        * @param interceptors   Collection of interceptors
        * @return Retrofit
        */
        fun retrofitClient(baseHost: HttpUrl, interceptors: Collection<Interceptor>?): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseHost)
                .client(NetworkSDK.client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        /*
        * build client
        * @param interceptors: collection of interceptors
        * @return RetrofitClient
        */
        fun retrofitClient(interceptors: Collection<Interceptor>?): Retrofit {
            if (ApplicationUrlContainer.getInstance().getBaseUrl() != null &&
                ApplicationUrlContainer.getInstance().getBaseUrl()?.appBaseUrl.isNullOrEmpty()
            ) {
                throw NullPointerException("base URL empty")
            }

            return retrofitClient(
                HttpUrl.Builder()
                    .scheme("https")
                    .host(ApplicationUrlContainer.getInstance().getBaseUrl()?.appBaseUrl).build(),
                interceptors
            )
        }

        // build retrofit client
        fun getClient(interceptors: Collection<Interceptor>?): OkHttpClient {
            val trustManager = generateOpenTrustManager()

            //adding network and application as common interceptors
            var clientBuilder = OkHttpClient.Builder()
                .addInterceptor(NetworkConnectivityInterceptor())
                .addInterceptor(ApplicationMetaDataInterceptor())
                .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.MILLISECONDS)

            //debug only
            if (BuildConfig.DEBUG) {
                clientBuilder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))

            }

            //interceptors for a particular enpoint
            interceptors?.forEach { interceptor: Interceptor -> clientBuilder.addInterceptor(interceptor) }
            return clientBuilder.build()
        }

        // ssl context basic
        private fun getSSLContext(certificateArray: Array<TrustManager>): SSLContext {
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, certificateArray, SecureRandom())
            return sslContext
        }

        // socket factory from ssl context
        private fun getSocketFactory(sslContext: SSLContext): SSLSocketFactory {
            return sslContext.socketFactory
        }


        /*
        * accept any certificates from the end point
        */
        private fun generateOpenTrustManager(): Array<TrustManager> {
            return arrayOf(object : X509TrustManager {
                @SuppressLint("TrustAllX509TrustManager")
                override fun checkClientTrusted(p0: Array<out X509Certificate>?, p1: String?) {
                }

                @SuppressLint("TrustAllX509TrustManager")
                override fun checkServerTrusted(p0: Array<out X509Certificate>?, p1: String?) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return emptyArray()
                }
            })
        }


    }


    //factory class for generating api service
    class Factory {
        public fun <T : Any?> getRestService(service: Class<T>, interceptors: Collection<Interceptor>?): T {
            return retrofitClient(interceptors).create(service)
        }

        public fun <T : Any?> getRestService(service: Class<T>): T {
            return retrofitClient(emptyList()).create(service)
        }


        public fun <T : Any?> getRestService(
            service: Class<T>,
            baseHost: String,
            interceptors: Collection<Interceptor>?
        ): T {
            return retrofitClient(
                HttpUrl.Builder().host(baseHost).build(),
                interceptors
            ).create(service)
        }

        public fun <T : Any?> getRestService(
            service: Class<T>,
            url: HttpUrl,
            interceptors: Collection<Interceptor>?
        ): T {
            return retrofitClient(
                url,
                interceptors
            ).create(service)
        }
    }
}

