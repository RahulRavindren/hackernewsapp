package com.hackernewsapplication.common.viewmodel

import com.hackernewsapplication.common.basecommons.BaseRepository
import com.hackernewsapplication.common.basecommons.BaseViewModel
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

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

    inline fun <T : BaseViewModel> onCreate(annotatedClass: KClass<*>): T {
        if (annotatedClass.annotations.isNotEmpty()) {
            val viewModelAnnotation = annotatedClass.annotations[0] as ViewModel<*>
            val viewModelInstance = viewModelAnnotation.kClass.createInstance()
            try {
                INSTANCE.repository?.let {
                    (viewModelInstance as BaseViewModel).setRepo(it)
                }

            } catch (e: ClassCastException) {
                throw ClassCastException("viewModel does not inherit BaseViewModel")
            }
            return viewModelInstance as T
        } else {
            throw IllegalArgumentException("no viewmodel annotations for class")
        }
    }
}