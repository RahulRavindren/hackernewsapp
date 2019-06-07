package com.hackernewsapplication.android

import android.content.Context
import com.hackernewsapplication.android.entity.NewsEntity
import com.hackernewsapplication.android.view.listing.service.NewsStoryService
import com.hackernewsapplication.common.C
import com.hackernewsapplication.common.entity.RepoIndentifier
import com.hackernewsapplication.common.utils.AppConfigBuilder
import com.hackernewsapplication.common.utils.ApplicationUrlContainer
import com.hackernewsapplication.network.NetworkSDK
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
                applicationId = "com.sample.android"
                applicationCode = 0
                env = ""
            }
        )

        newsStoriesService = NewsStoryService()
    }

    @Test
    fun `test api for item detail`() {
        if (::newsStoriesService.isInitialized) {
            val result = newsStoriesService.fetch(RepoIndentifier(C.NEWS_ENTITY, 8863)).blockingGet()
            assertNewsEntity(result)
        }
    }

    private fun assertNewsEntity(entity: NewsEntity) {
        val compareEntity = NewsEntity(
            id = 8863,
            by = "dhouston",
            descendants = 71,
            score = 104,
            time = 1175714200,
            title = "My YC app: Dropbox - Throw away your USB drive",
            type = "story",
            url = "http://www.getdropbox.com/u/2/screencast.html"
        )

        Assert.assertTrue(
            entity.id == compareEntity.id && entity.by == compareEntity.by
                    && entity.descendants == compareEntity.descendants && entity.score == compareEntity.score
                    && entity.time == compareEntity.time && entity.title == compareEntity.title
                    && entity.type == compareEntity.type && entity.url == compareEntity.url
        )
    }



}