package com.hackernewsapplication.android.view.listing.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.hackernewsapplication.android.entity.NewsEntity
import com.hackernewsapplication.android.interfaces.NewsItemDataFetchCallback
import com.hackernewsapplication.common.basecommons.BaseViewHolder
import kotlinx.android.synthetic.main.top_news_viewholder_item.*

/**
 * @Author rahulravindran
 */
class NewsListingItemViewHolder(val view: View, val callback: NewsItemDataFetchCallback) :
    RecyclerView.ViewHolder(view), BaseViewHolder {

    override val containerView: View?
        get() = view

    override fun onBind(data: Any) {
        val entity = data as NewsEntity

        top_news_header.text = entity.title
    }

    override fun onAttach() {

    }

    override fun onDetach() {

    }
}