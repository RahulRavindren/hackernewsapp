package com.hackernewsapplication.android.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hackernewsapplication.android.NewsHomeActivity
import com.hackernewsapplication.android.R
import com.hackernewsapplication.android.entity.NewsEntity
import com.hackernewsapplication.android.interfaces.ItemDataFetchCallback
import com.hackernewsapplication.android.view.listing.presenter.NewsListingPresenter
import com.hackernewsapplication.android.view.listing.view.NewsLisitingFragmentView
import com.hackernewsapplication.android.view.listing.viewholder.NewsListingItemViewHolder
import com.hackernewsapplication.common.C
import com.hackernewsapplication.common.basecommons.BaseViewHolder
import com.hackernewsapplication.common.utils.Logger
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

/**
 * @Author rahulravindran
 */
class ListingFragment : BaseListingFragment<NewsEntity>(), ListingAdapterType, NewsLisitingFragmentView,
    ItemDataFetchCallback {
    private var presenter: NewsListingPresenter? = null
    private var compositeDisposable = CompositeDisposable()
    private val tagName = ListingFragment::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = NewsListingPresenter()
        presenter?.attachView(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val savedData = savedInstanceState?.getParcelableArray(C.NEWS_ENTITY_LIST)
        if (!savedData.isNullOrEmpty()) {
            Single.just(savedData.toList()).subscribe(getListingObserver())
        } else {
            setPagination(false)
            setRefresh(true)
            showRefresh(true)
            presenter?.start()
            presenter?.subscribe(getListingObserver(), true)
        }

    }

    override fun onRefresh() {
        showRefresh(true)
        presenter?.subscribe(getListingObserver(), false)
    }

    override fun getViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val rootView = LayoutInflater.from(context).inflate(R.layout.top_news_viewholder_item, viewGroup, false)
        return NewsListingItemViewHolder(rootView, this)
    }

    override fun attachViewHolderData(holder: RecyclerView.ViewHolder, position: Int, data: Any?) {
        if (data != null && holder is BaseViewHolder) {
            holder.onBind(data)
        }
    }

    override fun onItemClick(position: Int, data: Any?, bundle: Bundle?) {
        if ((data as? NewsEntity)?.kids.isNullOrEmpty()) return
        (activity as? NewsHomeActivity)?.getNavigation()?.navigate(R.id.news_detail_fragment, bundle)
    }

    override fun onfetchNewsForId(storyId: Int, viewHolder: RecyclerView.ViewHolder, viewHolderPos: Int) {
        Logger.debug(tagName, "storyId : $storyId , viewholder Pos : $viewHolderPos")
        compositeDisposable.add(presenter?.fetchStory(storyId)?.observeOn(AndroidSchedulers.mainThread())
            ?.onErrorResumeNext { Single.never() }?.subscribe { entity, error ->
                adapter()?.updateItemAtPos(
                    entity,
                    viewHolderPos
                )
            }!!
        )
    }
}
