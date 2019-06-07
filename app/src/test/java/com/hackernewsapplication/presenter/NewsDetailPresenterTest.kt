package com.hackernewsapplication.presenter

import android.content.Context
import com.hackernewsapplication.android.view.detail.presenter.NewsDetailPresenter
import com.hackernewsapplication.android.view.detail.view.NewsDetailView
import com.hackernewsapplication.android.view.listing.service.NewsFetchService
import com.hackernewsapplication.android.view.listing.service.NewsStoryService
import com.hackernewsapplication.common.utils.TestSchedulerProvider
import com.hackernewsapplication.network.NetworkSDK
import com.hackernewsapplication.network.RetrofitAdapter
import io.reactivex.schedulers.TestScheduler
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NewsDetailPresenterTest {
    lateinit var presenter: NewsDetailPresenter
    lateinit var server: MockWebServer

    @Mock
    lateinit var view: NewsDetailView

    @Mock
    lateinit var context: Context

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        server = MockWebServer()
        server.start()

        NetworkSDK.init(context)
        presenter = NewsDetailPresenter(TestSchedulerProvider(TestScheduler()))
        presenter.attachView(view)
        val service = NewsStoryService(
            RetrofitAdapter.Factory().getRestService(
                NewsFetchService::class.java,
                server.url("/"),
                listOf()
            )
        )
        presenter.initRepository(service)
    }

    @Test
    fun `test if error view interface called`() {
    }

}