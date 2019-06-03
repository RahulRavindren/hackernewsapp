package com.hackernewsapplication.android.view.listing.service

import com.hackernewsapplication.common.entity.RepoIndentifier
import com.nytimes.android.external.store3.base.Fetcher
import io.reactivex.Single

/**
 * @Author rahulravindran
 */

class NewsLisitingService : Fetcher<Any, RepoIndentifier> {

    init {
        val service: lazy {

        }
    }

    override fun fetch(key: RepoIndentifier): Single<Any> {

    }
}