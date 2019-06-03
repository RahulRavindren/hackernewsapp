package com.hackernewsapplication.network.utils

import com.hackernewsapplication.network.exceptions.ServerException
import io.reactivex.functions.Consumer
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * @Author rahulravindran
 */
class StoreNetworkErrorHandling(val listener: NetworkViewType) : Consumer<Throwable> {

    override fun accept(e: Throwable?) {

        if (e is HttpException) {
            val httpException = e as HttpException
            listener.onNetworkError(ServerException(e))

        } else if (e is SocketTimeoutException) {
            throw e
        } else if (e is IOException) {
            val exception = e as IOException
            listener.onNetworkError()
        }
    }

}