package com.hackernewsapplication.common.utils

import com.hackernewsapplication.common.C

/**
 * @Author rahulravindran
 */
class AppConfigBuilder {
    var env: String = C.EMPTY_STRING
    var applicationId: String = C.EMPTY_STRING
        private set
    var applicationVersion: String = C.EMPTY_STRING
        private set
    var applicationCode: Int = -1
        private set

    private constructor()

    private constructor(builder: Builder) {
        this.env = builder.env
        if (builder.applicationId.isNullOrEmpty()) {
            throw  NullPointerException("application Id is null")
        }
        this.applicationId = builder.applicationId
        this.applicationVersion = builder.applicationVersion
        if (builder.applicationCode == -1) {
            throw IllegalArgumentException("application code cannot be -1")
        }
        this.applicationCode = builder.applicationCode
    }


    companion object {
        private var INSTANCE: AppConfigBuilder? = null
        fun getInstance(): AppConfigBuilder? {
            return INSTANCE
        }

        fun getInstance(builder: Builder): AppConfigBuilder? {
            if (INSTANCE == null) {
                synchronized(AppConfigBuilder::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE =
                            AppConfigBuilder(builder)
                    }
                }
            }
            return INSTANCE
        }
    }


    class Builder() {
        lateinit var env: String
        lateinit var applicationId: String
        lateinit var applicationVersion: String
        var applicationCode: Int = -1


        fun setApplicationEnv(env: String): Builder {
            this.env = env;
            return this
        }

        fun setApplicationID(id: String): Builder {
            this.applicationId = id
            return this
        }

        fun setApplicationVersion(version: String): Builder {
            this.applicationVersion = version
            return this
        }

        fun setApplicationCode(code: Int): Builder {
            this.applicationCode = code
            return this
        }


        fun build() {
            getInstance(this)
        }
    }
}