package com.hackernewsapplication.android.view.detail.presenter

import com.hackernewsapplication.android.entity.NewsEntity
import com.hackernewsapplication.android.view.detail.view.NewsDetailView
import com.hackernewsapplication.android.view.listing.repository.NewsStoryRepository
import com.hackernewsapplication.android.view.listing.service.NewsStoryService
import com.hackernewsapplication.common.basecommons.BasePresenter
import io.reactivex.Single

class NewsDetailPresenter : BasePresenter<NewsDetailView>() {
    var repository: NewsStoryRepository? = null

    override fun start() {
        super.start()
        repository = NewsStoryRepository(NewsStoryService())
    }

    private fun fetchStory(entity: NewsEntity): Single<NewsEntity> =
        repository?.fetchCommentsAndReplies(entity.id) ?: Single.never<NewsEntity>()
}