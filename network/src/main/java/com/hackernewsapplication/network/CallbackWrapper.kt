package com.hackernewsapplication.network

import com.hackernewsapplication.network.exceptions.ServerException
import com.hackernewsapplication.network.utils.NetworkViewType
import io.reactivex.observers.DisposableObserver
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * @Author rahulravindran
 *  Class CallbackWrapper for Rxjava API call. That does global simple network error handling
 *  Used for making request bypassing the Store pipeline.
 */


abstract class CallbackWrapper<T>(val listener: NetworkViewType?) : DisposableObserver<T>() {
    abstract fun onSuccess(t: T)

    override fun onComplete() {
        dispose()
    }

    override fun onNext(t: T) {
        onSuccess(t)
    }

    override fun onError(e: Throwable) {
        if (listener == null) {
            throw NullPointerException("network type listener is null")
        }

        if (e is HttpException) {
            val httpException = e as HttpException
            listener?.onNetworkError(ServerException(e))

        } else if (e is SocketTimeoutException) {
            throw e
        } else if (e is IOException) {
            val exception = e as IOException
            listener.onNetworkError()
        }

        onComplete()
    }
}
