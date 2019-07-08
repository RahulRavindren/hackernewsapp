package com.hackernewsapplication.common.viewmodel

import com.hackernewsapplication.common.basecommons.BaseRepository
import com.hackernewsapplication.common.basecommons.BaseViewModel

class ViewModelFactory private constructor(private val repository: BaseRepository) {
    object INSTANCE {
        var repository: BaseRepository? = null

        fun instance(repository: BaseRepository): ViewModelFactory {
            this.repository = repository
            return ViewModelFactory(repository)
        }
    }

    companion object {
        fun getInstance(repository: BaseRepository): ViewModelFactory = INSTANCE.instance(repository)
    }

    inline fun <reified T> onCreate(): T? {
        if (T::class.annotations.isNotEmpty()) {
            val viewModelAnnotation = T::class.annotations[0] as ViewModel<*>
            try {
                INSTANCE.repository?.let {
                    (viewModelAnnotation.kClass.objectInstance as BaseViewModel).setRepo(it)
                }

            } catch (e: ClassCastException) {
                throw ClassCastException("viewModel does not inherit BaseViewModel")
            }
            return viewModelAnnotation::kClass.get().objectInstance as T
            TODO("inject repository into the viewmodel")

        } else {
            throw IllegalArgumentException("no viewmodel annotations for class")
        }
    }
}