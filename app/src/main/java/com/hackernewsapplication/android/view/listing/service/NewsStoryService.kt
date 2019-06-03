package com.hackernewsapplication.android.view.listing.service

import com.hackernewsapplication.android.entity.NewsEntity
import com.hackernewsapplication.common.entity.RepoIndentifier
import com.hackernewsapplication.network.RetrofitAdapter
import com.nytimes.android.external.store3.base.Fetcher
import io.reactivex.Single

class NewsStoryService : Fetcher<NewsEntity, RepoIndentifier> {

    val service: NewsFetchService by lazy { RetrofitAdapter.Factory().getRestService(NewsFetchService::class.java) }

    override fun fetch(key: RepoIndentifier): Single<NewsEntity> = service.fetchItem(key.id)
}