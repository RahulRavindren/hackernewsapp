package com.hackernewsapplication.android.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hackernewsapplication.android.NewsHomeActivity
import com.hackernewsapplication.android.R
import com.hackernewsapplication.android.entity.NewsEntity
import com.hackernewsapplication.android.interfaces.NewsItemDataFetchCallback
import com.hackernewsapplication.android.view.listing.presenter.NewsListingPresenter
import com.hackernewsapplication.android.view.listing.view.NewsLisitingFragmentView
import com.hackernewsapplication.android.view.listing.viewholder.NewsListingItemViewHolder
import com.hackernewsapplication.common.basecommons.BaseViewHolder

/**
 * @Author rahulravindran
 */
class NewsListingFragment : BaseListingFragment<NewsEntity>(), ListingAdapterType, NewsLisitingFragmentView,
    NewsItemDataFetchCallback {
    private var presenter: NewsListingPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = NewsListingPresenter()
        presenter?.attachView(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPagination(false)
        setRefresh(true)
        presenter?.start()
        presenter?.subscribe(getListingObserver(), true)
    }

    override fun onRefresh() {
        presenter?.subscribe(getListingObserver(), false)
    }

    override fun getViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val rootView = LayoutInflater.from(context).inflate(R.layout.top_news_viewholder_item, viewGroup, false)
        return NewsListingItemViewHolder(rootView)
    }

    override fun attachViewHolderData(holder: RecyclerView.ViewHolder, position: Int, data: Any) {
        if (holder is BaseViewHolder) {
            holder.onBind(data)
        }
    }

    override fun onItemClick(position: Int, data: Any, bundle: Bundle?) {
        (activity as? NewsHomeActivity)?.getNavigation()?.navigate(R.id.news_detail_fragment, bundle)
    }

    override fun onfetchNewsForId(id: Int, viewHolder: RecyclerView.ViewHolder, viewHolderPos: Int) {

    }
}