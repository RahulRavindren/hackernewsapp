package com.hackernewsapplication.network.utils

import com.hackernewsapplication.network.exceptions.ServerException

/**
 * @Author rahulravindran
 */
interface NetworkViewType {

    //for network connection
    fun onNetworkError()

    //server based error exception transform
    fun onNetworkError(exception: ServerException)

    //refined message and code
    fun onNetworkError(message: String, code: Int)
}