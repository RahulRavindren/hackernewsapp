package com.hackernewsapplication.common.utils

import android.os.Process

/**
 * @Author rahulravindran
 * Logging Exception Handler thread for application
 */
class LoggingUncaughtExceptionHandler(val defaultHandler: Thread.UncaughtExceptionHandler) :
    Thread.UncaughtExceptionHandler {
    private var crashing: Boolean = false

    override fun uncaughtException(p0: Thread?, p1: Throwable?) {
        try {
            if (crashing) return
            crashing = true

            Logger.debug(
                "FATAL EXCEPTION",
                p0?.name + " PID: " + Process.myPid()
            )
        } finally {
            defaultHandler.uncaughtException(p0, p1)
        }
    }
}