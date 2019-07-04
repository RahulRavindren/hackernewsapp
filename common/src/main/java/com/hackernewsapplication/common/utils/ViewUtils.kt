package com.hackernewsapplication.common.utils

import android.os.Build
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

/**
 * @Author rahulravindran
 */
object ViewUtils {

    fun showToast(view: View, message: String, duration: Int = 0) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            Snackbar.make(view, message, duration).show()
        } else {
            Toast.makeText(view.context, message, duration)
        }
    }

}
