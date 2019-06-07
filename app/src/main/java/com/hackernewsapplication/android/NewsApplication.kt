package com.hackernewsapplication.android

import android.app.Application
import android.content.res.Configuration
import com.hackernewsapplication.common.utils.AppConfigBuilder
import com.hackernewsapplication.common.utils.ApplicationUrlContainer
import com.hackernewsapplication.common.utils.Logger
import com.hackernewsapplication.common.utils.Utils
import com.hackernewsapplication.network.NetworkSDK

/**
 * @Author rahulravindran
 */
class NewsApplication : Application() {
    private val TAG = NewsApplication::class.java.simpleName

    override fun onCreate() {
        super.onCreate()
        Utils.application = this

        NetworkSDK.init(this)

        Utils.setStrictModePolicy(true)
        //RxJavaPlugins.setErrorHandler(Utils.undeliveredExceptionHandler())

        Logger.init()

        AppConfigBuilder.getInstance(
            AppConfigBuilder.Builder().apply {
                applicationCode = BuildConfig.VERSION_CODE
                applicationId = BuildConfig.APPLICATION_ID
                applicationVersion = BuildConfig.VERSION_NAME
                env = BuildConfig.BUILD_TYPE
            }
        )

        ApplicationUrlContainer.init(
            ApplicationUrlContainer.Builder().apply {
                applicationBaseUrl = BuildConfig.BASE_HOST_URL
            }
        )

    }

    override fun onTerminate() {
        Logger.info(TAG, "termination of application")
        super.onTerminate()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        Logger.info(TAG, "config changed ")
        super.onConfigurationChanged(newConfig)

    }

}
