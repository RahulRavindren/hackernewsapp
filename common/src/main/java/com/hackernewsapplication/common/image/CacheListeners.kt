package com.hackernewsapplication.common.image

import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.engine.cache.MemoryCache
import com.bumptech.glide.manager.ConnectivityMonitor
import com.hackernewsapplication.common.utils.Logger

object CacheListeners {
    val memoryCacheListener = object : MemoryCache.ResourceRemovedListener {
        val TAG = "GLIDE_MEM"
        override fun onResourceRemoved(removed: Resource<*>) {
            Logger.info(TAG, "resource removed ${removed.resourceClass} of size ${removed.size}")
        }
    }
    val imageRequestConnectivityListener = object : ConnectivityMonitor.ConnectivityListener {
        val TAG = "GLIDE_NETWORK"
        override fun onConnectivityChanged(isConnected: Boolean) {
            Logger.info(TAG, "")
        }
    }

}