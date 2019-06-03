package com.hackernewsapplication.common.utils

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnCanceledListener

/**
 * @Author rahulravindran
 */
object LocationManager {

    @SuppressLint("MissingPermission")
    fun performActionOnLastLocation(context: Context?, action: (Location?) -> Unit) {
        if (context == null) {
            throw  NullPointerException("context cannot be null")
        }
        LocationServices.getFusedLocationProviderClient(context)
            .lastLocation.apply {
            addOnSuccessListener { location ->
                kotlin.run {
                    if (location != null) {
                        Logger.debug(
                            "LOCATION",
                            "lat :: " + location.latitude + " lon :: " + location.longitude
                        )
                        action(location)
                    }
                }
            }
            addOnFailureListener { exception ->
                kotlin.run {
                    Logger.error(exception)
                    action(null)
                }
            }
            addOnCanceledListener {
                object : OnCanceledListener {
                    override fun onCanceled() {
                        action(null)
                    }
                }
            }
        }
    }

}