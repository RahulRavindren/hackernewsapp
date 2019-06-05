package com.hackernewsapplication.android.view.listing.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.hackernewsapplication.android.entity.NewsEntity
import com.hackernewsapplication.android.interfaces.NewsItemDataFetchCallback
import com.hackernewsapplication.common.basecommons.BaseViewHolder

/**
 * @Author rahulravindran
 */
class NewsListingItemViewHolder(val view: View, val callback: NewsItemDataFetchCallback) :
    RecyclerView.ViewHolder(view), BaseViewHolder {
    var entity: NewsEntity? = null
    override val containerView: View?
        get() = view

    override fun onBind(data: Any) {
        if ((data as NewsEntity).id != itemId.toInt()) return

        entity = data
        if (entity?.title?.isEmpty() == true && entity?.type?.isEmpty() == true) {
            callback.onfetchNewsForId(entity?.id ?: -1, this, adapterPosition)
        } else {
            top_news_header.text = entity?.title
        }
    }

    override fun onAttach() {

    }

    override fun onDetach() {

    }

    override fun onDestroy() {
        entity = null
    }
}