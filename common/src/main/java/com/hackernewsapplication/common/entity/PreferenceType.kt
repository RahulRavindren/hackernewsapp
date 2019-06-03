package com.hackernewsapplication.common.entity

/**
 * @Author rahulravindran
 */
enum class PreferenceType {
    APP_STATE("appStatePreferences"),
    APP_CRENDENTIAL("appCredentialPreferences"),
    USER_DETAIL("userDetailPrefences");


    var fileName: String
        private set

    constructor(fileName: String) {
        this.fileName = fileName
    }
}