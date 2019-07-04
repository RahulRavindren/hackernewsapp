package com.hackernewsapplication.common.common.utils

import okhttp3.mockwebserver.MockWebServer

object Utils {
    fun mockServer(): MockWebServer {
        return MockWebServer()
    }
}
