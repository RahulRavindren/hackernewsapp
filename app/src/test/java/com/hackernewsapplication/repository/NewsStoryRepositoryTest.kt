package com.hackernewsapplication.repository

import android.content.Context
import com.hackernewsapplication.android.view.listing.repository.NewsStoryRepository
import com.hackernewsapplication.android.view.listing.service.NewsFetchService
import com.hackernewsapplication.android.view.listing.service.NewsStoryService
import com.hackernewsapplication.network.NetworkSDK
import com.hackernewsapplication.network.RetrofitAdapter
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException

@RunWith(MockitoJUnitRunner::class)
class NewsStoryRepositoryTest {
    lateinit var server: MockWebServer
    lateinit var respository: NewsStoryRepository

    @Mock
    lateinit var context: Context

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        server = MockWebServer()
        server.start()

        NetworkSDK.init(context)

        val service = RetrofitAdapter.Factory().getRestService(NewsFetchService::class.java, server.url("/"), listOf())
        respository = NewsStoryRepository(NewsStoryService(service))

    }

    @Test
    fun `repository test error case`() {
        //error response code
        server.enqueue(
            MockResponse()
                .setResponseCode(500)
        )

        val testObserver = this.respository.fetchCommentsAndReplies(8863).test()
        testObserver.awaitTerminalEvent()
        testObserver.assertError(HttpException::class.java)
    }


}