package com.hackernewsapplication.android.interfaces

import androidx.recyclerview.widget.RecyclerView

/**
 * @Author rahulravindran
 */
interface ItemDataFetchCallback {
    fun onfetchNewsForId(id: Int, viewHolder: RecyclerView.ViewHolder, viewHolderPos: Int)
}
