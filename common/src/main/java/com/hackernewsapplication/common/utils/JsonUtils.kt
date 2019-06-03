package com.hackernewsapplication.common.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonSyntaxException
import com.hackernewsapplication.common.C
import java.lang.reflect.Type


/**
 * @Author rahulravindran
 */
class JsonUtils {

    companion object {

        fun <T> fromJson(jsonString: String?, classOfT: Class<T>): T? {

            val builder = GsonBuilder()

            val gson = builder.create()
            var value: T? = null
            try {
                value = gson.fromJson(jsonString, classOfT)
            } catch (e: JsonSyntaxException) {
                e.printStackTrace()
            }

            return value
        }


        fun <T> fromJson(jsonString: String, typeOfT: Type): T? {

            try {
                return fromJsonOrThrow<T>(
                    jsonString,
                    typeOfT
                )
            } catch (e: JsonSyntaxException) {
                return null
            }

        }

        fun <T> fromJsonOrThrow(jsonString: String, typeOfT: Type): T {
            val builder = GsonBuilder()
            val gson = builder.create()
            return gson.fromJson(jsonString, typeOfT)
        }


        fun <T> fromJson(jsonElement: JsonElement?, typeOfT: Type): T? {
            if (jsonElement == null) {
                return null
            }

            val builder = GsonBuilder()
            val gson = builder.create()
            var value: T? = null
            try {
                value = gson.fromJson(jsonElement, typeOfT)
            } catch (e: JsonSyntaxException) {
                e.printStackTrace()
            }

            return value
        }

        fun <T : Any> toJson(value: T): String {
            if (value == null) {
                return C.EMPTY_STRING
            }

            val gson = Gson()
            var jsonString: String = C.EMPTY_STRING

            try {
                jsonString = gson.toJson(value, value::class.java)
            } catch (e: JsonSyntaxException) {
                e.printStackTrace()
            }
            return jsonString
        }

    }

}