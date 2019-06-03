package com.hackernewsapplication.common.basecommons

/**
 * @Author rahulravindran
 */
interface BaseRespositoryUseCase<T> {
    fun init()
    fun destory() {}
}