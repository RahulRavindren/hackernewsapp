package com.hackernewsapplication.android.view.listing.service

import com.hackernewsapplication.android.entity.NewsEntity
import com.hackernewsapplication.common.entity.RepoIndentifier
import com.hackernewsapplication.network.RetrofitAdapter
import com.nytimes.android.external.store3.base.Fetcher
import io.reactivex.Single

open class NewsStoryService : Fetcher<NewsEntity, RepoIndentifier> {
    var service: NewsFetchService? = null

    constructor() {
        service = lazy { RetrofitAdapter.Factory().getRestService(NewsFetchService::class.java) }.value
    }

    constructor(service: NewsFetchService) {
        this.service = service
    }

    override fun fetch(key: RepoIndentifier): Single<NewsEntity> = service?.fetchItem(key.id) ?: Single.never()
}
