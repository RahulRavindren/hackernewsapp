package com.hackernewsapplication.android.view.listing.presenter

import com.hackernewsapplication.android.entity.NewsEntity
import com.hackernewsapplication.android.view.listing.repository.NewsListingRepository
import com.hackernewsapplication.android.view.listing.repository.NewsStoryRepository
import com.hackernewsapplication.android.view.listing.service.NewsStoryService
import com.hackernewsapplication.android.view.listing.service.TopStoriesService
import com.hackernewsapplication.android.view.listing.view.NewsLisitingFragmentView
import com.hackernewsapplication.common.C
import com.hackernewsapplication.common.basecommons.BasePresenter
import com.hackernewsapplication.common.entity.RepoIndentifier
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * @Author rahulravindran
 */
class NewsListingPresenter : BasePresenter<NewsLisitingFragmentView>() {
    private var repository: NewsListingRepository? = null
    private var itemFetchRepository: NewsStoryRepository? = null

    override fun start() {
        super.start()
        repository = NewsListingRepository(TopStoriesService())
        itemFetchRepository = NewsStoryRepository(NewsStoryService())
    }

    fun subscribe(observer: SingleObserver<List<NewsEntity>>, fromCache: Boolean = false) {
        fetchTopStories(fromCache).observeOn(AndroidSchedulers.mainThread()).subscribe(observer)
    }

    private fun fetchTopStories(fromCache: Boolean = false): Single<List<NewsEntity>> {
        return repository?.fetchTopStories(fromCache) ?: Single.never()
    }

    fun fetchStory(itemId: Int): Single<NewsEntity> {
        return itemFetchRepository?.fetch(RepoIndentifier(C.NEWS_ENTITY, itemId)) ?: Single.never()
    }

    override fun stop() {
        super.stop()
    }
}
