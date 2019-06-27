package com.hackernewsapplication.common.basecommons

import com.hackernewsapplication.common.interfaces.BaseView
import com.hackernewsapplication.common.utils.Logger
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.jetbrains.annotations.TestOnly

/**
 * @Author rahulravindran
 */
open class BasePresenter<T : BaseView> {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val TAG = BasePresenter::class.java.simpleName
    private var view: T? = null

    protected fun getView(): T? {
        return this.view
    }

    @TestOnly
    fun getViewForTest(): T? = this.view

    open fun attachView(view: T?) {
        if (view == null) {
            throw NullPointerException("view is null")
        }
        this.view = view
    }

    open fun detachView() {
        this.view = null
    }

    open fun start() {
        Logger.debug(TAG, "presenter start")
        //show loading when progress begins
        view?.showProgress(true)
    }

    open fun stop() {
        Logger.debug(TAG, "presenter stop")
        compositeDisposable.dispose()
    }


    //add rx observer to disposable
    protected fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }


}