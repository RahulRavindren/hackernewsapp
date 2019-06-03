package com.hackernewsapplication.android.view.listing.service

import com.hackernewsapplication.android.entity.NewsEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @Author rahulravindran
 */
interface NewsFetchService {
    @GET("/v0/item/{itemId}.json")
    fun fetchItem(@Path("itemId") itemId: Int): Single<NewsEntity>

    @GET("/v0/topstories.json")
    fun fetchTopNews(): Single<Set<Int>>

}