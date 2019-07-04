package com.hackernewsapplication.common.utils

import com.hackernewsapplication.common.entity.BaseUrl
import org.jetbrains.annotations.TestOnly

/**
 * @Author rahulravindran
 */
class ApplicationUrlContainer private constructor() {
    var runLocalBuild: Boolean = false

    private var port: Int = -1
        get

    private object HOLDER {
        val INSTANCE by lazy { ApplicationUrlContainer() }
    }

    fun setPort(port: Int) {
        this.port = port
    }

    fun getPort(): Int {
        return port
    }


    fun getBaseUrl(): BaseUrl? {
        checkNotNull(baseUrl)
        return baseUrl
    }


    companion object {
        private var baseUrl: BaseUrl? = null

        @JvmStatic
        fun getInstance(): ApplicationUrlContainer {
            return HOLDER.INSTANCE
        }

        @TestOnly
        @JvmStatic
        fun init(baseUrl: BaseUrl) {
            this.baseUrl = baseUrl
        }

        @JvmStatic
        fun init(builder: Builder?) {
            baseUrl =
                BaseUrlBuilder.getStoredBaseUrl(Utils.application)
            if (baseUrl == null) {
                baseUrl = builder?.build()
                overrideUrl(builder)
            }
        }

        @JvmStatic
        fun overrideUrl(builder: Builder?) {
            BaseUrlBuilder.overrideStoredBaseUrl(
                Utils.application,
                builder?.build()
            )
        }


    }


    class Builder {
        var applicationBaseUrl: String? = null
        var applicationAnalyticsUrl: String? = null
        var applicationBaseUrl1: String? = null

        fun build(): BaseUrl {
            return BaseUrl(
                applicationBaseUrl,
                applicationAnalyticsUrl,
                applicationBaseUrl1

            )
        }
    }


}
