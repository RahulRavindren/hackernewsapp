package com.hackernewsapplication.common.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.hackernewsapplication.common.entity.BaseUrl

private const val NETWORK_SHARED_PREF = "network_shared_pref"
private const val NETWORK_BASE_URL = "NETWORK_BASE_URL"

object BaseUrlBuilder {

    fun getStoredBaseUrl(context: Context?): BaseUrl? {
        val sharePref = getNetworkSharedPref(context)
        val baseUrlString = sharePref?.getString(NETWORK_BASE_URL, "")
        if (baseUrlString.isNullOrEmpty()) {
            return null
        } else {
            return Gson().fromJson(baseUrlString, BaseUrl::class.java)
        }
    }

    fun overrideStoredBaseUrl(context: Context?, baseUrl: BaseUrl?) {
        val sharedPref = getNetworkSharedPref(context)
        val edit = sharedPref?.edit()
        edit?.putString(NETWORK_BASE_URL, Gson().toJson(baseUrl))
        edit?.commit()
    }

    private fun getNetworkSharedPref(context: Context?): SharedPreferences? {
        return context?.getSharedPreferences(NETWORK_SHARED_PREF, Context.MODE_PRIVATE)
    }
}
