package com.hackernewsapplication.android.entity

import java.io.Serializable

/**
 * @Author rahulravindran
 */
class NewsEntity(
    val by: String,
    val descendants: Int,
    val id: Int,
    val kids: Set<Int>,
    val score: Int,
    val time: Long,
    val title: String,
    val type: String,
    val url: String,
    val parent: Int,
    val poll: Long
) : Serializable