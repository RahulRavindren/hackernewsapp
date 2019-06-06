package com.hackernewsapplication.common.utils

import android.content.Context
import android.util.Log
import timber.log.Timber
import java.io.File
import java.nio.charset.Charset
import java.text.SimpleDateFormat
import java.util.*

/**
 * @Author rahulravindran
 */
class Logger {
    companion object {
        private val LOG_FILE: String = "app_log_" + Calendar.getInstance().time + ".txt"


        public fun init() {
            if (Utils.debug) {
                Timber.plant(Timber.DebugTree(), CrashReportingLocal())
            } else {
                Timber.plant(CrashReportingLocal())
            }
        }

        public fun debug(tag: String?, message: String) {
            Timber.tag(tag)
            Timber.asTree().d(message)
        }

        public fun debug(message: String) {
            Timber.asTree().d(message)
        }

        public fun info(tag: String?, message: String) {
            Timber.tag(tag)
            Timber.asTree().i(message)
        }

        public fun info(message: String) {
            Timber.asTree().i(message)
        }

        public fun error(tag: String?, message: String) {
            Timber.tag(tag)
            Timber.asTree().e(message)
        }

        public fun error(message: String) {
            Timber.asTree().e(message)
        }

        public fun error(t: Throwable?) {
            Timber.asTree().e(t)
        }

        public fun error(tag: String?, t: Throwable) {
            Timber.asTree().e(tag, t.message)
            t.printStackTrace()
        }
    }


    private constructor() {
        throw AssertionError("no instances")
    }

    class ApplicationDebugTree : Timber.DebugTree() {

    }

    class CrashReportingLocal : Timber.Tree() {
        override fun isLoggable(priority: Int): Boolean {
            return priority >= Log.WARN
        }

        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {

            if (priority == Log.DEBUG || priority == Log.INFO) {
                return
            }

            val logfile = File(
                Utils.application?.filesDir,
                LOG_FILE
            )
            if (!logfile.exists()) {
                logfile.createNewFile()
            }
            val logTimeStamp: String = SimpleDateFormat(
                "E MMM dd yyyy 'at' hh:mm:ss:SSS aaa",
                Locale.getDefault()
            ).format(Date())
            Utils.application?.openFileOutput(logfile.name, Context.MODE_PRIVATE).use {
                val writeMessage = logTimeStamp + "   " + message as String
                try {
                    it?.write(writeMessage.toByteArray(Charset.defaultCharset()))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}