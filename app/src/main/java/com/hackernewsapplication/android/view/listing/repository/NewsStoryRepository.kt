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

    fun fetchCommentsAndReplies(entity: NewsEntity): Single<List<NewsEntity>> {
        if (entity.kids.isNullOrEmpty()) {
            return Single.never()
        } else {
            entity.kids.forEach { _ -> fetchCommentsWithLevels(entity) }
            return Single.just(entity.commentsEntity)
        }
    }

    private fun fetchCommentsWithLevels(entity: NewsEntity, level: Int = 0) {
        while (level < 1 && entity.kids.size > 1) {
            addToDisposable(fetch<NewsEntity>(RepoIndentifier(C.COMMENTS_ENTITY, entity.kids[level]))
                .subscribe { t: NewsEntity? ->
                    entity.commentsEntity.add(t!!)
                    fetchCommentsWithLevels(t, level + 1)
                })
        }
    }

}