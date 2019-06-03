package com.hackernewsapplication.common.customviews

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * @Author rahulravindran
 *  Custom Recylerview with pagination option
 */

class CustomVerticalRecyclerView : RecyclerView {
    private var isPaginated = false
    private var listenerList: MutableList<PaginationListener> = mutableListOf()

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)

    fun setPagination(state: Boolean) {
        isPaginated = state
    }

    fun removeListener(listener: PaginationListener) {
        if (listenerList.contains(listener)) {
            listenerList.remove(listener)
        }
    }

    fun addListener(listener: PaginationListener) {
        removeListener(listener)
        listenerList.add(listener)
    }

    fun clearAllListeners() {
        listenerList.clear()
    }

    fun init() {
        setOnScrollListener(CustomScrollListener())
        if (adapter != null) {
            listenerList.forEach { listener: PaginationListener -> listener.fetchFirst() }
        }
    }

    override fun canScrollHorizontally(direction: Int): Boolean {
        return false
    }

    //inner class Scrolllistener
    inner class CustomScrollListener : RecyclerView.OnScrollListener() {
        private var state = RecyclerView.SCROLL_STATE_IDLE
        private val FIXED_THRESHOLD_COUNT = 3;


        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            if (state == RecyclerView.SCROLL_STATE_SETTLING && dy != 0) {
                if (!isPaginated) {
                    //false on pagination skip next block
                    return
                }
                val adapter = recyclerView.adapter
                val lytMng = recyclerView.layoutManager

                if (adapter == null || lytMng == null) {
                    return
                }


                var firstVisibleItemPos = -1
                if (lytMng is LinearLayoutManager) {
                    firstVisibleItemPos = (lytMng as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
                } else {
                    firstVisibleItemPos = (lytMng as GridLayoutManager).findFirstCompletelyVisibleItemPosition()
                }

                if (firstVisibleItemPos > adapter?.itemCount - 1 - FIXED_THRESHOLD_COUNT) {
                    listenerList.forEach { listener: PaginationListener -> listener.fetchNext() }
                }
            }

            super.onScrolled(recyclerView, dx, dy)
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            state = newState
            super.onScrollStateChanged(recyclerView, newState)
        }
    }

    interface PaginationListener {
        fun fetchNext()
        fun fetchFirst()

    }

}