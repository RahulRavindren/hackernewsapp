package com.hackernewsapplication.android.view.listing.repository

import com.hackernewsapplication.android.entity.NewsEntity
import com.hackernewsapplication.common.basecommons.BaseRepository
import com.hackernewsapplication.common.entity.BaseIdentifier
import com.hackernewsapplication.common.entity.RepoIndentifier
import com.nytimes.android.external.store3.base.Fetcher
import io.reactivex.Single

/**
 * @Author rahulravindran
 */
class NewsListingRepository(fetcher: Fetcher<List<NewsEntity>, RepoIndentifier>) :
    BaseRepository(fetcher = fetcher as Fetcher<List<NewsEntity>, BaseIdentifier>) {


    fun fetchTopStories(fromCache: Boolean = false): Single<List<NewsEntity>> {
        return if (fromCache) fetchWithCache(RepoIndentifier.TOP_STORIES_ALL)
        else fetch(RepoIndentifier.TOP_STORIES_ALL)
    }

}
