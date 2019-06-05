package com.hackernewsapplication.common.utils

import android.app.Application
import android.content.Intent
import android.os.StrictMode
import com.google.gson.Gson
import com.hackernewsapplication.common.C
import io.reactivex.exceptions.OnErrorNotImplementedException
import io.reactivex.functions.Consumer
import io.reactivex.subjects.PublishSubject
import java.io.File
import java.io.IOException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * @Author rahulravindran
 */
class Utils {
    companion object {
        var application: Application? = null
            set(value) {
                field = value
            }
            get() = field

        val gson = Gson()

        var debug: Boolean = true
        var eventSubject = PublishSubject.create<Event>()

        fun startActivity(intent: Intent) {
            application?.startActivity(intent)
        }


        fun getDeviceHeight(): Int {
            return application!!.resources.displayMetrics.heightPixels
        }

        fun getDeviceWidth(): Int {
            return application!!.resources.displayMetrics.widthPixels
        }


        fun getCacheDir(dirname: String): File {
            val cacheDir = application?.filesDir
            val httpCacheDir = File(cacheDir, dirname)
            if (httpCacheDir != null) {
                httpCacheDir.mkdirs()
            }
            return httpCacheDir
        }

        fun getCacheDir(): File {
            return getCacheDir(C.CACHE_DIR_NAME)
        }


        fun getString(id: Int): String {
            return application?.getString(id) ?: ""
        }

        fun getString(id: Int, vararg params: String): String? {
            return application?.getString(id, params)
        }


        fun getStringArray(id: Int): Array<String>? {
            return application?.resources?.getStringArray(id)
        }


        fun formatDate(pattern: String, date: String): Date? {
            val dateFormat = SimpleDateFormat(pattern)
            try {
                val date = dateFormat.parse(date)
                return date
            } catch (e: ParseException) {
                e.printStackTrace()
                return null
            }
        }

        fun formateDate(pattern: String, date: Date?): String {
            val dateFormat = SimpleDateFormat(pattern)
            try {
                return dateFormat.format(date)
            } catch (e: ParseException) {
                e.printStackTrace()
                return C.EMPTY_STRING
            }
        }


        fun setStrictModePolicy(state: Boolean) {
            if (state) {
                StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .build()
                StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectActivityLeaks()
                    .detectLeakedClosableObjects()
                    .detectLeakedRegistrationObjects()
                    .penaltyLog()
                    .build()
            }
        }


        fun undeliveredExceptionHandler(): Consumer<Throwable> {
            return Consumer {
                if (it is OnErrorNotImplementedException) {
                    Thread.currentThread().uncaughtExceptionHandler.uncaughtException(Thread.currentThread(), it)
                }

                if (it is IOException) {
                    Logger.error("IO Exception")
                    it.printStackTrace()

                }

                if (it is InterruptedException || it.cause is InterruptedException) {
                    Logger.error("Interrupte exception")
                    it.printStackTrace()
                }

                if (it is NullPointerException || it is IllegalArgumentException) {
                    Logger.error("Undelivered exception")
                    it.printStackTrace()
                    Thread.currentThread().uncaughtExceptionHandler.uncaughtException(Thread.currentThread(), it)
                }

                if (it is IllegalStateException) {
                    Logger.error("Undeliverable exception")
                    Thread.currentThread().uncaughtExceptionHandler.uncaughtException(Thread.currentThread(), it)
                }

                Logger.error("Undeliverable exception not sure what to do")
                Logger.error(it)
            }


        }


    }

    data class Event(val message: String?, val throwable: Throwable)


}
