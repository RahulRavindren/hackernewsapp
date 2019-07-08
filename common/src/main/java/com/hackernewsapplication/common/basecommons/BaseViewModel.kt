package com.hackernewsapplication.common.basecommons

import androidx.lifecycle.ViewModel

class BaseViewModel : ViewModel() {
    protected var repository: BaseRepository? = null

    fun setRepo(repository: BaseRepository) {
        this.repository = repository
    }
}