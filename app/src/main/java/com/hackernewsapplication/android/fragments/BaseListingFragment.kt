package com.hackernewsapplication.android.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.idling.CountingIdlingResource
import com.hackernewsapplication.android.R
import com.hackernewsapplication.android.entity.NewsEntity
import com.hackernewsapplication.android.interfaces.RecyclerOnClickListener
import com.hackernewsapplication.android.interfaces.RecylerClickListenerBundleMock
import com.hackernewsapplication.common.C
import com.hackernewsapplication.common.basecommons.BaseFragment
import com.hackernewsapplication.common.basecommons.BaseViewHolder
import com.hackernewsapplication.common.utils.Logger
import com.hackernewsapplication.common.utils.bundleOf
import com.hackernewsapplication.common.utils.mapToTypedArray
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.base_fragment_listing.*

open class BaseListingFragment<T> : BaseFragment(), RecyclerOnClickListener {
    protected var isStateRestored: Boolean = false
    @VisibleForTesting
    var clickMock: RecylerClickListenerBundleMock? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootview = inflater.inflate(R.layout.base_fragment_listing, container, false)
        return rootview
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
    }

    open fun getAdapterType(): ListingAdapterType = this as ListingAdapterType

    private fun initRefreshListener() {
        refresh_list.setOnRefreshListener { getAdapterType().onRefresh() }
    }

    private fun initList() {
        base_listing.let {
            it.layoutManager = LinearLayoutManager(it.context, RecyclerView.VERTICAL, false)
            it.adapter = ListingAdapter(this, this, getAdapterType())
        }

    }

    fun setPagination(state: Boolean) {
        base_listing.setPagination(state)
    }

    fun setRefresh(state: Boolean = true) {
        refresh_list.isEnabled = state
        if (state) {
            initRefreshListener()
        }
    }

    fun showRefresh(state: Boolean) {
        refresh_list?.isRefreshing = state
    }


    fun setRefreshState(state: Boolean) {
        refresh_list?.isRefreshing = state
    }

    @VisibleForTesting
    open fun idlingResource(): CountingIdlingResource? = null

    fun <T> getListingObserver(): SingleObserver<T> = base_listing?.adapter as SingleObserver<T>


    fun adapter(): ListingAdapter? = base_listing?.adapter as? ListingAdapter

    class ListingAdapter(
        private val ref: BaseListingFragment<*>,
        private val recyclerOnClickListener: RecyclerOnClickListener,
        private val adapterType: ListingAdapterType
    ) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>(), SingleObserver<List<Any>> {

        private val tagName = ListingFragment::class.java.simpleName
        private var dataItems: MutableList<Any> = mutableListOf()

        init {
            setHasStableIds(true)
        }

        override fun onSuccess(t: List<Any>) {
            ref.showRefresh(false)
            dataItems = t.toMutableList()
            notifyDataSetChanged()
        }

        override fun getItemId(position: Int): Long {
            return (dataItems[position] as NewsEntity).id.toLong()
        }

        fun getDataItems(): List<Any> = dataItems


        override fun onSubscribe(d: Disposable) {

        }

        override fun onError(e: Throwable) {
            Logger.error(e)
        }

        fun updateItemAtPos(data: Any, position: Int) {
            Logger.debug(tagName, "updating data at pos $position")
            dataItems[position] = data
            notifyItemChanged(position)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return adapterType.getViewHolder(parent, viewType)
        }


        override fun getItemCount(): Int = dataItems?.size ?: 0

        @SuppressLint("VisibleForTests")
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            holder.itemView.setOnClickListener {
                recyclerOnClickListener.onItemClick(
                    position, dataItems[position], bundleOf(
                        C.NEWS_ENTITY to dataItems[position]
                    )
                )

                //for testing
                ref.clickMock?.onClickPassBundle(
                    bundleOf(
                        C.NEWS_ENTITY to dataItems[position]
                    )
                )
            }
            adapterType.attachViewHolderData(holder, position, dataItems?.get(position))
        }

        override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
            super.onViewAttachedToWindow(holder)
            if (holder is BaseViewHolder) {
                holder.onAttach()
            }
        }

        override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
            super.onViewDetachedFromWindow(holder)
            if (holder is BaseViewHolder) {
                holder.onDetach()
            }
        }

        override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
            if (holder is BaseViewHolder) {
                holder.onDestroy()
            }
            super.onViewRecycled(holder)
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        base_listing?.layoutManager?.onRestoreInstanceState(savedInstanceState?.getParcelable(C.RECYCLERVIEW_STATE))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        val recyclerviewState = base_listing?.layoutManager?.onSaveInstanceState()
        outState.putParcelable(C.RECYCLERVIEW_STATE, recyclerviewState)
        outState.putParcelableArray(C.NEWS_ENTITY_LIST, adapter()?.getDataItems()?.mapToTypedArray { it as NewsEntity })
        super.onSaveInstanceState(outState)

    }
}

interface ListingAdapterType {
    fun getViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    fun attachViewHolderData(holder: RecyclerView.ViewHolder, position: Int, data: Any?)
    fun onRefresh() {}
}
