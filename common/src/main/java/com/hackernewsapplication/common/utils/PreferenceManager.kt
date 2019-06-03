package com.hackernewsapplication.common.utils

import android.content.Context
import android.content.SharedPreferences
import com.hackernewsapplication.common.C
import com.hackernewsapplication.common.entity.PreferenceType
import com.hackernewsapplication.common.interfaces.SavedPreference


/**
 * @Author rahulravindran
 */
object PreferenceManager {
    fun savePreference(savedPreference: SavedPreference, value: Any) {
        savePreference(
            savedPreference.getPreferenceType(),
            savedPreference.getName(),
            value
        )
    }

    fun savePreference(context: Context?, savedPreference: SavedPreference, value: Any) {
        savePreference(
            context,
            savedPreference.getPreferenceType(),
            savedPreference.getName(),
            value
        )
    }

    fun savePreference(preferenceType: PreferenceType?, key: String, value: Any) {
        savePreference(
            Utils.application,
            preferenceType,
            key,
            value
        )
    }


    fun savePreference(context: Context?, preferenceType: PreferenceType?, key: String, value: Any) {
        var filename: String? = PreferenceType.APP_STATE.fileName
        filename = preferenceType?.fileName
        val preferences: SharedPreferences? = context?.getSharedPreferences(filename, Context.MODE_PRIVATE)
        val editor = preferences?.edit()

        if (value is String) {
            editor?.putString(key, value.toString())
        } else if (value is Int) {
            val intValue = value as Int
            if (intValue != -1) {
                editor?.putInt(key, intValue)
            }
        } else if (value is Long) {
            val longValue = value
            if (longValue != -1) {
                editor?.putLong(key, longValue)
            }
        } else if (value is Boolean) {
            val booleanValue = value as Boolean
            if (booleanValue != null) {
                editor?.putBoolean(key, booleanValue)
            }
        } else if (value is Set<*>) {
            val stringSet = value as Set<String>
            editor?.putStringSet(key, stringSet)
        } else if (value is Float) {
            val floatValue = value as Float
            if (java.lang.Float.compare(floatValue, -1.0f) != 0) {
                editor?.putFloat(key, floatValue)
            }
        }
        editor?.apply()
    }


    fun savePreferenceIfChanged(savedPreference: SavedPreference, value: Any?): Boolean {
        if (value == null) {
            return false
        }

        if (value is String) {
            val savedPref = getPreference<String>(
                savedPreference,
                C.EMPTY_STRING
            )
            if (savedPref != C.EMPTY_STRING && savedPref == value.toString()) {
                // already value is saved. no need to edit
            } else {
                savePreference(
                    savedPreference.getPreferenceType(),
                    savedPreference.getName(),
                    value
                )
            }
        } else if (value is Int) {
            val savedPref = getPreference(
                savedPreference,
                -1
            )!!
            if (savedPref != -1 && savedPref == value.toInt()) {
                // already value is saved. no need to edit
            } else {
                savePreference(
                    savedPreference.getPreferenceType(),
                    savedPreference.getName(),
                    value
                )
            }
        } else if (value is Long) {
            val savedPref = getPreference(
                savedPreference,
                -1L
            ) as Long
            if (!savedPref.equals(-1) && savedPref == value.toLong()) {
                // already value is saved. no need to edit
            } else {
                savePreference(
                    savedPreference.getPreferenceType(),
                    savedPreference.getName(),
                    value
                )
            }
        } else if (value is Boolean) {
            val savedPref =
                getPreference(savedPreference, false)
            if (savedPref !== value) {
                savePreference(
                    savedPreference.getPreferenceType(),
                    savedPreference.getName(),
                    value
                )
            }
        }

        return true
    }

    fun saveStringSet(key: String, value: Set<String>) {
        savePreference(null, key, value)
    }

    fun saveString(key: String, value: String) {
        savePreference(null, key, value)
    }

    fun saveInt(key: String, value: Int) {
        savePreference(null, key, value)
    }

    fun saveLong(key: String, value: Long) {
        savePreference(null, key, value)
    }

    fun saveFloat(key: String, value: Float) {
        savePreference(null, key, value)
    }

    fun saveLong(savedPreference: SavedPreference, value: Long) {
        savePreference(
            savedPreference.getPreferenceType(),
            savedPreference.getName(),
            value
        )
    }

    fun saveBoolean(key: String, value: Boolean) {
        savePreference(null, key, value)
    }

    fun <T> getPreference(savedPreference: SavedPreference, defaultVal: T): T? {
        return getPreference(
            savedPreference.getPreferenceType(), savedPreference.getName(),
            defaultVal
        )
    }

    fun <T> getPreference(context: Context, savedPreference: SavedPreference, defaultVal: T): T {
        return getPreference(
            context, savedPreference.getPreferenceType(), savedPreference.getName(),
            defaultVal
        )
    }

    fun <T> getPreference(preferenceType: PreferenceType?, key: String, defaultVal: T): T {
        val context = Utils.application
        return getPreference(
            context,
            preferenceType,
            key,
            defaultVal
        )
    }

    fun <T> getPreference(context: Context?, preferenceType: PreferenceType?, key: String, defaultVal: T): T {
        if (context == null) {
            return defaultVal
        }
        var fileName = PreferenceType.APP_STATE.fileName
        if (preferenceType != null) {
            fileName = preferenceType.fileName
        }
        val sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        if (defaultVal is String) {
            return sharedPreferences.getString(key, defaultVal.toString()) as T

        } else if (defaultVal is Int) {
            val defIntValue = defaultVal as Int
            val intValue = sharedPreferences.getInt(key, defIntValue)
            return intValue as T

        } else if (defaultVal is Long) {
            val defLongValue = defaultVal as Long
            val longValue = sharedPreferences.getLong(key, defLongValue)
            return longValue as T

        } else if (defaultVal is Boolean) {
            val defBooleanValue = defaultVal as Boolean
            val booleanValue = sharedPreferences.getBoolean(key, defBooleanValue)
            return booleanValue as T
        } else if (defaultVal is Set<*>) {
            val setValue = sharedPreferences.getStringSet(key, defaultVal as Set<String>)
            return setValue as T
        } else if (defaultVal is Float) {
            val defFloatValue = defaultVal as Float
            val floatValue = sharedPreferences.getFloat(key, defFloatValue)
            return floatValue as T
        }

        return defaultVal
    }

    fun getString(key: String): String {
        return getString(
            key,
            C.EMPTY_STRING
        )
    }

    fun getString(key: String, defaultValue: String): String {
        return getPreference<String>(null, key, defaultValue)
    }

    fun getStringSet(key: String, defaultValue: Set<String>): Set<String> {
        return getPreference<Set<String>>(
            null,
            key,
            defaultValue
        )
    }

    fun getInt(key: String): Int {
        return getInt(key, Integer.MIN_VALUE)
    }

    fun getInt(key: String, defaultValue: Int): Int {
        return getPreference<Int>(null, key, defaultValue)
    }

    fun getLong(key: String): Long {
        return getLong(key, java.lang.Long.MIN_VALUE)
    }

    fun getLong(key: String, defaultValue: Long): Long {
        return getPreference<Long>(null, key, defaultValue)
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return getPreference<Boolean>(null, key, defaultValue)
    }

    fun remove(key: String) {
        remove(key, null)
    }

    fun remove(savedPreference: SavedPreference) {
        remove(
            savedPreference.getName(),
            savedPreference.getPreferenceType()
        )
    }

    private fun remove(key: String, preferenceType: PreferenceType?) {
        var fileName = key
        if (preferenceType != null) {
            fileName = preferenceType.fileName
        }
        val sharedPreferences = Utils.application?.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.remove(key)
        editor?.apply()
    }
}