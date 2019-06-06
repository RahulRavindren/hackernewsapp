package com.hackernewsapplication.android.view.detail.presenter

import com.hackernewsapplication.android.entity.NewsEntity
import com.hackernewsapplication.android.view.detail.view.NewsDetailView
import com.hackernewsapplication.android.view.listing.repository.NewsStoryRepository
import com.hackernewsapplication.android.view.listing.service.NewsStoryService
import com.hackernewsapplication.common.basecommons.BasePresenter
import com.hackernewsapplication.common.utils.Scheduler
import io.reactivex.Single
import org.jetbrains.annotations.TestOnly

class NewsDetailPresenter(private val scheduler: Scheduler) : BasePresenter<NewsDetailView>() {
    var repository: NewsStoryRepository? = null

    override fun start() {
        super.start()
        repository = NewsStoryRepository(NewsStoryService())
    }

    @TestOnly
    fun initRepository(service: NewsStoryService) {
        repository = NewsStoryRepository(service)
    }

    fun fetchComment(commentId: Int): Single<NewsEntity> =
        repository?.fetchCommentsAndReplies(commentId)?.observeOn(scheduler.ui())?.doOnError {
            getView()?.showErrorPage(true)
        } ?: Single.never<NewsEntity>()
}