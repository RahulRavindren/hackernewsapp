package com.hackernewsapplication.android.view.listing.repository

import com.hackernewsapplication.common.basecommons.BaseRepository
import com.hackernewsapplication.common.entity.BaseIdentifier
import com.hackernewsapplication.common.entity.RepoIndentifier
import com.nytimes.android.external.store3.base.Fetcher

/**
 * @Author rahulravindran
 */
class NewsListingRepository(fetcher: Fetcher<Any, RepoIndentifier>) :
    BaseRepository(fetcher = fetcher as Fetcher<*, BaseIdentifier>) {


}