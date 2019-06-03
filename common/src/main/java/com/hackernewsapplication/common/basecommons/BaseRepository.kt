package com.hackernewsapplication.common.basecommons

import com.hackernewsapplication.common.entity.BaseIdentifier
import com.hackernewsapplication.common.entity.RepoIndentifier
import com.nytimes.android.external.store3.base.Fetcher
import com.nytimes.android.external.store3.base.impl.*
import io.reactivex.Single
import java.util.concurrent.TimeUnit

/**
 * @Author rahulravindran
 */
const val STORE_SIZE = 10 * 1024

open class BaseRepository(val fetcher: Fetcher<*, BaseIdentifier>) : BaseRespositoryUseCase<Any> {
    private var mStore: Store<*, BaseIdentifier>? = null

    init {
        this.init()
    }

    override fun init() {
        initStore()
    }

    fun initStore() {
        mStore = FluentStoreBuilder.key(fetcher) {
            stalePolicy = StalePolicy.REFRESH_ON_STALE
            memoryPolicy = default()
        }
    }

    fun default(): MemoryPolicy {
        return FluentMemoryPolicyBuilder.build() {
            memorySize = STORE_SIZE * 1L
            expireAfterTimeUnit = TimeUnit.SECONDS
        }
    }

    fun fetch(key: RepoIndentifier): Single<*>? = mStore?.get(key)

}