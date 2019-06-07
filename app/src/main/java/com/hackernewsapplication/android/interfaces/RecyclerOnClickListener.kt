package com.hackernewsapplication.android.interfaces

import android.content.Intent
import android.os.Bundle

interface RecyclerOnClickListener {
    // implemented not needed everywhere
    fun onItemClick(position: Int, data: Any?, intent: Intent? = null) {}

    fun onItemClick(position: Int, data: Any?, intent: Bundle? = null) {}
}
