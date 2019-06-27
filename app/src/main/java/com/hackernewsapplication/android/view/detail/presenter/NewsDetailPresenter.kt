package com.hackernewsapplication.android.view.detail.presenter

import androidx.annotation.VisibleForTesting
import com.hackernewsapplication.android.entity.NewsEntity
import com.hackernewsapplication.android.view.detail.view.NewsDetailView
import com.hackernewsapplication.android.view.listing.repository.NewsStoryRepository
import com.hackernewsapplication.android.view.listing.service.NewsStoryService
import com.hackernewsapplication.common.basecommons.BasePresenter
import com.hackernewsapplication.common.utils.Scheduler
import io.reactivex.Single

open class NewsDetailPresenter(private val scheduler: Scheduler) : BasePresenter<NewsDetailView>() {
    var repository: NewsStoryRepository? = null

    override fun start() {
        super.start()
        repository = NewsStoryRepository(NewsStoryService())
    }

    @VisibleForTesting
    fun stubRepo(repository: NewsStoryRepository) {
        this.repository = repository
    }

    fun fetchComment(commentId: Int): Single<NewsEntity> =
        repository?.fetchCommentsAndReplies(commentId)
            ?.onErrorResumeNext { Single.error(it) }
            ?.doOnError { getView()?.showErrorPage(true) }?.observeOn(scheduler.ui())
            ?: Single.never<NewsEntity>()
}
