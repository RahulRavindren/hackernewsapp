package com.hackernewsapplication.common.utils

import com.hackernewsapplication.common.entity.Permission

/**
 * @Author rahulravindran
 * Abstract for permission adapter
 */
abstract class PermissionAdapter {

    abstract fun getPermissions(): List<Permission>
    fun shouldShowRationale(): Boolean {
        return true
    }

    abstract fun permissionDenied()
}