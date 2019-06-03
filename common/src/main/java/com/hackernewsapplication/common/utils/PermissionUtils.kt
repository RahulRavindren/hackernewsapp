package com.hackernewsapplication.common.utils

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.hackernewsapplication.common.entity.Permission

/**
 * @Author rahulravindran
 */

fun ContextCompat.checkPermissions(
    activity: AppCompatActivity,
    permissions: List<Permission>, action: () -> Unit
) {
    permissions.forEach { permission: Permission ->
        run {
            if (ContextCompat.checkSelfPermission(activity, permission.permissionString)
                != PackageManager.PERMISSION_GRANTED
            ) {
                action
            }
        }
    }
}