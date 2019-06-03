package com.hackernewsapplication.android.view.listing.repository

import com.hackernewsapplication.android.entity.NewsEntity
import com.hackernewsapplication.common.basecommons.BaseRepository
import com.hackernewsapplication.common.entity.BaseIdentifier
import com.hackernewsapplication.common.entity.RepoIndentifier
import com.nytimes.android.external.store3.base.Fetcher

class NewsStoryRepository(fetcher: Fetcher<NewsEntity, RepoIndentifier>) :
    BaseRepository(fetcher = fetcher as Fetcher<NewsEntity, BaseIdentifier>) {

    fun fetchNewsStory(key: RepoIndentifier, noCache: Boolean = false) = fetchWithCache<NewsEntity>(key)

}