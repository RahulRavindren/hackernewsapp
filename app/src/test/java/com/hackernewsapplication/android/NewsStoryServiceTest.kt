package com.hackernewsapplication.android

import android.content.Context
import com.hackernewsapplication.android.view.listing.service.NewsStoryService
import com.hackernewsapplication.common.utils.AppConfigBuilder
import com.hackernewsapplication.common.utils.ApplicationUrlContainer
import com.hackernewsapplication.network.NetworkSDK
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * @Author rahulravindran
 */
@RunWith(MockitoJUnitRunner::class)
class NewsStoryServiceTest {
    lateinit var newsStoriesService: NewsStoryService
    @Mock
    lateinit var context: Context

    @Before
    fun setUp() {
        ApplicationUrlContainer.init(
            ApplicationUrlContainer.Builder().apply {
                applicationBaseUrl = "hacker-news.firebaseio.com"
            }.build()
        )
        MockitoAnnotations.initMocks(this)
        NetworkSDK.init(context)
        AppConfigBuilder.getInstance(
            AppConfigBuilder.Builder().apply {
                applicationVersion = ""
                applicationId = ""
                applicationCode = 0
                env = ""
            }
        )

        newsStoriesService = NewsStoryService()
    }

    fun ``


}