package com.hackernewsapplication.common.interfaces

import org.jetbrains.annotations.TestOnly

/**
 * @Author rahulravindran
 */
interface FetchListener {
    @TestOnly
    fun doneFetching()

    @TestOnly
    fun beginFetching()
}