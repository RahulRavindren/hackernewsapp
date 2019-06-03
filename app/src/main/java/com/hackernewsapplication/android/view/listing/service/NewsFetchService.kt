package com.hackernewsapplication.android.view.listing.service

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @Author rahulravindran
 */
interface NewsFetchService {
    @GET("/v0/item/{itemId}.json")
    fun fetchItem(@Path("itemId") itemId: String): Single<>
}