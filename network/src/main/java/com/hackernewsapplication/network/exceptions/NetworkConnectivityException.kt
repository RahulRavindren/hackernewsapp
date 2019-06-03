package com.hackernewsapplication.network.exceptions

/**
 * @Author rahulravindran
 *  Network Exception class that carries a default error message
 */
class NetworkConnectivityException : Exception {
    constructor() : super()
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
    constructor(cause: Throwable?) : super(cause)
}