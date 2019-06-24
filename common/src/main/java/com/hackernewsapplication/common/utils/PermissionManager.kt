package com.hackernewsapplication.common.utils

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.hackernewsapplication.common.R
import com.hackernewsapplication.common.entity.Permission

/**
 * @Author rahulravindran
 * Permission Manager to manage permission after SDK_INT > 23
 */
class PermissionManager {
    private var permissionAdapter: PermissionAdapter? = null
    private var shouldShowPermissionRationale: Boolean = false

    constructor(permissionAdapter: PermissionAdapter?) {
        if (null == permissionAdapter) {
            throw NullPointerException("permission adapter null")
        }

        this.permissionAdapter = permissionAdapter
    }

    fun checkPermission(activity: AppCompatActivity) {
        askPermission(activity, permissionAdapter?.getPermissions()!!)
    }

    //ask list of permission
    fun askPermission(activity: AppCompatActivity, permissions: List<Permission>) {
        checkPermissions(activity, permissions) {
            //ask for permssion or show a dialog
            permissions.forEach { permission: Permission ->
                kotlin.run {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(
                            activity,
                            permission.permissionString
                        )
                    ) {
                        showPermissionDialog(activity, permission)

                    } else {
                        ActivityCompat.requestPermissions(
                            activity,
                            arrayOf(permission.permissionString),
                            permission.permission
                        )
                    }
                }
            }
        }
    }

    //ask single permission
    fun askPermission(activity: AppCompatActivity, permission: Permission) {
        checkPermissions(activity, listOf(permission)) {
            //ask for permision or show a dialog
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    activity,
                    permission.permissionString
                )
            ) {
                shouldShowPermissionRationale = true
                showPermissionDialog(activity, permission)

            } else {
                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(permission.permissionString), permission.permission
                )
            }
        }
    }


    //show permission dialog
    private fun showPermissionDialog(activity: AppCompatActivity, permission: Permission) {
        activity?.let {
            AlertDialog.Builder(it).apply {
                setMessage(R.string.default_permission_message)
                setTitle("")
                setCancelable(false)
                setPositiveButton(R.string.yes, object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        ActivityCompat.requestPermissions(
                            activity,
                            arrayOf(permission.permissionString), permission.permission
                        )
                    }
                })
                setNegativeButton(R.string.no, object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        permissionAdapter?.permissionDenied()
                    }
                })
            }
        }.show()

    }

    fun onRequestPermissionResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray, action: () -> Unit
    ) {
        permissions.forEachIndexed { index, s ->
            val result = grantResults[index]
            if (result == PackageManager.PERMISSION_GRANTED) {
                action()
            }
            if (!shouldShowPermissionRationale) {
                showSettingsToast()
            }
        }
    }

    fun showSettingsToast() {

    }

    //check permissions extension function
    fun PermissionManager.checkPermissions(
        activity: AppCompatActivity,
        permissions: List<Permission>, action: () -> Unit
    ) {
        if (Build.VERSION.SDK_INT > 23) {
            permissions.forEach { permission: Permission ->
                run {
                    if (ContextCompat.checkSelfPermission(activity, permission.permissionString)
                        != PackageManager.PERMISSION_GRANTED
                    ) {
                        action()
                    } else {
                        action()
                    }
                }
            }
        } else {
            action()
        }
    }
}

