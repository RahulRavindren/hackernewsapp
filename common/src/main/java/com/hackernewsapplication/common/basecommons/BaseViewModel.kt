package com.hackernewsapplication.common.basecommons

import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    protected var repository: BaseRepository? = null

    fun setRepo(repository: BaseRepository) {
        this.repository = repository
    }
}