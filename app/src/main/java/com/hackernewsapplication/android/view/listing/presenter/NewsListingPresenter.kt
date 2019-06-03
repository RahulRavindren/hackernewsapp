package com.hackernewsapplication.android.view.listing.presenter

import com.hackernewsapplication.android.view.listing.repository.NewsListingRepository
import com.hackernewsapplication.android.view.listing.service.NewsLisitingService
import com.hackernewsapplication.android.view.listing.view.NewsLisitingFragmentView
import com.hackernewsapplication.common.basecommons.BasePresenter

/**
 * @Author rahulravindran
 */
class NewsListingPresenter : BasePresenter<NewsLisitingFragmentView>() {
    private var repository: NewsListingRepository? = null

    override fun start() {
        super.start()
        repository = NewsListingRepository(NewsLisitingService())
    }

    override fun stop() {
        super.stop()
    }
}