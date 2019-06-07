package com.hackernewsapplication.android.view.listing.repository

import com.hackernewsapplication.android.entity.NewsEntity
import com.hackernewsapplication.common.C
import com.hackernewsapplication.common.basecommons.BaseRepository
import com.hackernewsapplication.common.entity.BaseIdentifier
import com.hackernewsapplication.common.entity.RepoIndentifier
import com.nytimes.android.external.store3.base.Fetcher
import io.reactivex.Single

class NewsStoryRepository(fetcher: Fetcher<NewsEntity, RepoIndentifier>) :
    BaseRepository(fetcher = fetcher as Fetcher<NewsEntity, BaseIdentifier>) {
    private val TAG = NewsStoryRepository::class.java.simpleName

    fun fetchCommentsAndReplies(storyId: Int): Single<NewsEntity> {
        return fetch<NewsEntity>(RepoIndentifier(C.NEWS_ENTITY, storyId))
    }

}
