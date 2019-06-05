package com.hackernewsapplication.android.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hackernewsapplication.android.R
import com.hackernewsapplication.android.entity.NewsEntity
import com.hackernewsapplication.android.view.detail.presenter.NewsDetailPresenter
import com.hackernewsapplication.android.view.detail.viewholder.CommentItemViewHolder
import com.hackernewsapplication.common.C
import com.hackernewsapplication.common.basecommons.BaseViewHolder
import io.reactivex.Single

/**
 * @Author rahulravindran
 */
class NewsDetailFragment : BaseListingFragment<NewsEntity>(), ListingAdapterType {
    private var presenter: NewsDetailPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = NewsDetailPresenter()
        presenter?.start()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPagination(false)
        setRefresh(false)
        val entity = arguments?.getSerializable(C.NEWS_ENTITY) as? NewsEntity
        entity ?: return


        Single.just(entity.kids.map { NewsEntity(id = it) }).subscribe(getListingObserver())
    }


    override fun getViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val rootView = LayoutInflater.from(context).inflate(R.layout.comment_viewholder_item, viewGroup, false)
        return CommentItemViewHolder(rootView)
    }

    override fun attachViewHolderData(holder: RecyclerView.ViewHolder, position: Int, data: Any?) {
        if (data != null && holder is BaseViewHolder) {
            holder.onBind(data)
        }
    }
}