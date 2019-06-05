package com.hackernewsapplication.android.view.listing.presenter

import com.hackernewsapplication.android.entity.NewsEntity
import com.hackernewsapplication.android.view.listing.repository.NewsListingRepository
import com.hackernewsapplication.android.view.listing.service.TopStoriesService
import com.hackernewsapplication.android.view.listing.view.NewsLisitingFragmentView
import com.hackernewsapplication.common.basecommons.BasePresenter
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * @Author rahulravindran
 */
class NewsListingPresenter : BasePresenter<NewsLisitingFragmentView>() {
    private var repository: NewsListingRepository? = null

    override fun start() {
        super.start()
        repository = NewsListingRepository(TopStoriesService())
    }

    fun subscribe(observer: SingleObserver<List<NewsEntity>>, fromCache: Boolean = false) {
        fetchTopStories(fromCache).observeOn(AndroidSchedulers.mainThread()).subscribe(observer)
    }

    private fun fetchTopStories(fromCache: Boolean = false): Single<List<NewsEntity>> {
        return repository?.fetchTopStories(fromCache) ?: Single.never()
    }

    override fun stop() {
        super.stop()
    }
}