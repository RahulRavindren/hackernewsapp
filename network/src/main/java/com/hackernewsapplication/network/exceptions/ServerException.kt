package com.hackernewsapplication.network.exceptions

/**
 * @Author rahulravindran
 */
class ServerException : Exception {

    constructor() : super()
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
    constructor(cause: Throwable?) : super(cause)
}