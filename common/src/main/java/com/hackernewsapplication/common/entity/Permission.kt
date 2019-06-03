package com.hackernewsapplication.common.entity

import android.Manifest
import androidx.annotation.IntegerRes

/**
 * @Author rahulravindran
 */
enum class Permission(@IntegerRes val permission: Int, val permissionString: String) {
    LOCATION(101, Manifest.permission.ACCESS_FINE_LOCATION)
}