package com.hackernewsapplication.common.basecommons

import com.hackernewsapplication.common.entity.BaseIdentifier
import com.hackernewsapplication.common.entity.RepoIndentifier
import com.nytimes.android.external.store3.base.Fetcher
import com.nytimes.android.external.store3.base.impl.*
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @Author rahulravindran
 */
const val STORE_SIZE = 10 * 1024

open class BaseRepository(val fetcher: Fetcher<*, BaseIdentifier>) : BaseRespositoryUseCase {
    private var store: Store<*, BaseIdentifier>? = null
    private val disposable: CompositeDisposable = CompositeDisposable()


    init {
        this.init()
    }

    override fun init() {
        initStore()
    }

    fun initStore() {
        store = FluentStoreBuilder.key(fetcher) {
            stalePolicy = StalePolicy.REFRESH_ON_STALE
        }
    }

    fun default(): MemoryPolicy {
        return FluentMemoryPolicyBuilder.build() {
            memorySize = STORE_SIZE * 1L
        }
    }

    fun <T> fetch(key: RepoIndentifier): Single<T> = store?.fetch(key) as? Single<T> ?: Single.never()

    fun <T> fetchWithCache(key: RepoIndentifier): Single<T> = store?.get(key) as? Single<T> ?: Single.never()

    override fun destory() {
        disposable.dispose()
    }

    override fun addToDisposable(disposable: Disposable) {
        this.disposable.add(disposable)
    }

}