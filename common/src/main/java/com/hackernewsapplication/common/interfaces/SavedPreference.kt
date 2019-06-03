package com.hackernewsapplication.common.interfaces

import com.hackernewsapplication.common.entity.PreferenceType

/**
 * @Author rahulravindran
 */
interface SavedPreference {
    fun getPreferenceType(): PreferenceType
    fun getName(): String
}