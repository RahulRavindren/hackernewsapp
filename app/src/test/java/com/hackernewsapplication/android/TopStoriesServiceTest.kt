package com.hackernewsapplication.android

import android.content.Context
import com.hackernewsapplication.android.entity.NewsEntity
import com.hackernewsapplication.android.view.listing.service.TopStoriesService
import com.hackernewsapplication.common.entity.RepoIndentifier
import com.hackernewsapplication.common.utils.AppConfigBuilder
import com.hackernewsapplication.common.utils.ApplicationUrlContainer
import com.hackernewsapplication.network.NetworkSDK
import io.reactivex.observers.TestObserver
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
class TopStoriesServiceTest {
    lateinit var topStoriesService: TopStoriesService
    val testObserver: TestObserver<List<NewsEntity>> = TestObserver()
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
        topStoriesService = TopStoriesService()
    }

    @Test
    fun `test api returning list newsentity with only id field`() {
        if (::topStoriesService.isInitialized) {
            val result = topStoriesService.fetch(RepoIndentifier.TOP_STORIES_ALL).blockingGet()
            //testing values to have id. Hence used this assertion
            Assert.assertTrue(result.all { it.id != -1 })
        }
    }

}
