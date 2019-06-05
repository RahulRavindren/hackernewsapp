package com.hackernewsapplication.android.entity

import java.io.Serializable

/**
 * @Author rahulravindran
 */
open class NewsEntity(
    val by: String = "",
    val descendants: Int = 0,
    val id: Int = -1,
    val kids: List<Int> = emptyList(),
    val score: Int = -1,
    val time: Long = -1,
    val title: String = "",
    val text: String = "",
    val type: String = "",
    val url: String = "",
    val parent: Int = -1,
    val poll: Long = -1,
    val commentsEntity: MutableList<NewsEntity> = mutableListOf()
) : Serializable