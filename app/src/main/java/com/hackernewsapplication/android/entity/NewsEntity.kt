package com.hackernewsapplication.android.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @Author rahulravindran
 */
@Parcelize
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
    val innerEntityList: MutableList<NewsEntity> = mutableListOf()
) : Parcelable {

    override fun equals(other: Any?): Boolean {
        other ?: return false
        return other == id
    }

    override fun hashCode(): Int {
        return id
    }
}
