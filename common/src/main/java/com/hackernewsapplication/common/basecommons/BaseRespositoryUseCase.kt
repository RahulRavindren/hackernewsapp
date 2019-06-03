package com.hackernewsapplication.common.basecommons

import io.reactivex.disposables.Disposable

/**
 * @Author rahulravindran
 */
interface BaseRespositoryUseCase {
    fun init()
    fun addToDisposable(disposable: Disposable) {}
    fun destory() {}
}