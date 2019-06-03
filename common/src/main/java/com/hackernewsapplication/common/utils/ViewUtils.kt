package com.hackernewsapplication.common.utils

import android.os.Build
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import org.joda.time.DateTime
import org.joda.time.Days

/**
 * @Author rahulravindran
 */
object ViewUtils {

    fun showToast(view: View, message: String, duration: Int = 0) {
        if (Build.VERSION.SDK_INT > 21) {
            Snackbar.make(view, message, duration).show()
        } else {
            Toast.makeText(view.context, message, duration)
        }
    }

    fun applicationSpecificDateTimeFormatter(unixStamp: Long): String? {
        val epochDate = DateTime(unixStamp * 1000L)
        val currentDate = DateTime.now()
        val days = Days.daysBetween(epochDate, currentDate).days
        return when {
            days > 7 && days < 14 -> "1 week ago"
            days >= 14 && days < 21 -> "2 week ago"
            days >= 21 && days < 28 -> "3 week ago"
            days > 29 && days / 30 < 12 -> "${days / 30} months ago"
            days / 30 >= 12 -> "${(days / 30) / 12} years ago"
            else -> ""
        }
    }

}

