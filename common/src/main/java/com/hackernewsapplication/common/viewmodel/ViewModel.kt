package com.hackernewsapplication.common.viewmodel

import kotlin.reflect.KClass


@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class ViewModel<T : Any>(val kClass: KClass<T>)