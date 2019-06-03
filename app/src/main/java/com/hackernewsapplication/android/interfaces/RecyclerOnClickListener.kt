package com.hackernewsapplication.android.interfaces

import android.content.Intent

interface RecyclerOnClickListener {
    fun onItemClick(position: Int, data: Any, intent: Intent) { // implemented not needed everywhere
    }
}