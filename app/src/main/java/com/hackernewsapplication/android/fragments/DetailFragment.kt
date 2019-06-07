package com.hackernewsapplication.android.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hackernewsapplication.android.R
import com.hackernewsapplication.android.entity.NewsEntity
import com.hackernewsapplication.android.interfaces.ItemDataFetchCallback
import com.hackernewsapplication.android.view.detail.presenter.NewsDetailPresenter
import com.hackernewsapplication.android.view.detail.viewholder.CommentItemViewHolder
import com.hackernewsapplication.common.C
import com.hackernewsapplication.common.basecommons.BaseViewHolder
import com.hackernewsapplication.common.utils.SchedulerProvider
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

/**
 * @Author rahulravindran
 */
class DetailFragment : BaseListingFragment<NewsEntity>(), ListingAdapterType, ItemDataFetchCallback {
    private var presenter: NewsDetailPresenter? = null
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = NewsDetailPresenter(SchedulerProvider())
        presenter?.start()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPagination(false)
        setRefresh(false)
        val entity = arguments?.getParcelable(C.NEWS_ENTITY) as? NewsEntity
        entity ?: return

        Single.just(entity.kids.map { NewsEntity(id = it) }).subscribe(getListingObserver())
    }


    override fun getViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val rootView = LayoutInflater.from(context).inflate(R.layout.comment_viewholder_item, viewGroup, false)
        return CommentItemViewHolder(rootView, this)
    }

    override fun attachViewHolderData(holder: RecyclerView.ViewHolder, position: Int, data: Any?) {
        if (data != null && holder is BaseViewHolder) {
            holder.onBind(data)
        }
    }

    override fun onfetchNewsForId(id: Int, viewHolder: RecyclerView.ViewHolder, viewHolderPos: Int) {
        presenter?.fetchComment(id)?.observeOn(AndroidSchedulers.mainThread())?.subscribe { result, error ->
            if (result != null) {
                (viewHolder as BaseViewHolder).onBind(result)
                if (result.kids.size > 1) {
                    presenter?.fetchComment(result.kids[0])?.observeOn(AndroidSchedulers.mainThread())
                        ?.subscribe { innerResult, _ ->
                            (viewHolder as CommentItemViewHolder).inflateNestedComments(
                                result
                            )
                        }
                }
            }
        }?.let { compositeDisposable.add(it) }
    }
}
