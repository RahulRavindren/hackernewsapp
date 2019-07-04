package com.hackernewsapplication.android.presenter

import com.hackernewsapplication.android.view.detail.presenter.NewsDetailPresenter
import com.hackernewsapplication.android.view.detail.view.NewsDetailView
import com.hackernewsapplication.android.view.listing.repository.NewsStoryRepository
import com.hackernewsapplication.common.utils.Logger
import com.hackernewsapplication.common.utils.TestSchedulerProvider
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NewsDetailPresenterTest {
    lateinit var presenter: NewsDetailPresenter

    @Mock
    lateinit var view: NewsDetailView

    lateinit var repository: NewsStoryRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = NewsDetailPresenter(TestSchedulerProvider(TestScheduler()))
        presenter.attachView(view)
        repository = mock(NewsStoryRepository::class.java)
        presenter.stubRepo(repository)
    }

    @Test
    fun `test if error view interface called`() {
        Mockito.`when`(repository.fetchCommentsAndReplies(1024)).thenReturn(Single.error(RuntimeException()))
        presenter.fetchComment(1024).subscribe { sucesss, error -> Logger.error(error) }
        Mockito.verify(view).showErrorPage(true)
    }

}
