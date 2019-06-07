package com.hackernewsapplication.android.view.listing.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.hackernewsapplication.android.entity.NewsEntity
import com.hackernewsapplication.android.interfaces.ItemDataFetchCallback
import com.hackernewsapplication.common.C
import com.hackernewsapplication.common.basecommons.BaseViewHolder
import kotlinx.android.synthetic.main.top_news_viewholder_item.*
import kotlinx.android.synthetic.main.top_news_viewholder_item_bottom_layout.*

/**
 * @Author rahulravindran
 */
class NewsListingItemViewHolder(val view: View, val callback: ItemDataFetchCallback) :
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
            author_details.text = "${entity?.score} points by ${entity?.by}"
            comment_details.text = " | ${entity?.kids?.size} comments "
        }
    }

    override fun onAttach() {

    }

    override fun onDetach() {

    }

    override fun onDestroy() {
        entity = null
        top_news_header.text = C.EMPTY_STRING
    }
}
