package com.hackernewsapplication.android.fragments

import android.os.Bundle
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hackernewsapplication.android.R
import com.hackernewsapplication.android.interfaces.RecyclerOnClickListener
import com.hackernewsapplication.common.basecommons.BaseFragment
import com.hackernewsapplication.common.utils.Logger
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.base_fragment_listing.*

open class BaseListingFragment<T> : BaseFragment(), RecyclerOnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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


    private fun initList() {
        base_listing.let {
            it.layoutManager = LinearLayoutManager(it.context, LinearLayoutManager.VERTICAL, false)
            it.adapter = ListingAdapter(this, getAdapterType())
        }
    }

    fun setPagination(state: Boolean) {
        base_listing.setPagination(state)
    }


    fun <T> getListingObserver(): SingleObserver<*>? = base_listing.adapter as SingleObserver<*>


    fun showProgress() {
        list_progress.visibility = View.VISIBLE
        base_listing.visibility = View.GONE
    }

    fun hideProgess() {
        list_progress.visibility = View.GONE
        base_listing.visibility = View.VISIBLE
    }

    fun adapter(): ListingAdapter = base_listing.adapter as ListingAdapter

    class ListingAdapter(val fragment: BaseListingFragment<*>, val adapterType: ListingAdapterType) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>(), SingleObserver<SparseArray<Any>> {

        private val TAG = ListingAdapter::class.java.simpleName
        private var dataItems = SparseArray<Any>()

        override fun onSuccess(t: SparseArray<Any>) {
            dataItems = t
            notifyDataSetChanged()
            fragment.hideProgess()
        }

        override fun onSubscribe(d: Disposable) {

        }

        override fun onError(e: Throwable) {
            Logger.error(e)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return adapterType.getViewHolder(parent, viewType)
        }

        override fun getItemCount(): Int = dataItems.size()

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            adapterType.attachViewHolderData(holder, position, dataItems[position])
        }
    }

}

interface ListingAdapterType {
    fun getViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    fun attachViewHolderData(holder: RecyclerView.ViewHolder, position: Int, data: Any)
}