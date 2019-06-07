package com.hackernewsapplication.android.fragments

import com.hackernewsapplication.android.entity.NewsEntity
import org.junit.Before
import org.junit.Test
import org.junit.internal.runners.JUnit4ClassRunner
import org.junit.runner.RunWith

@RunWith(JUnit4ClassRunner::class)
class BaseLisitingFragmentTest {
    lateinit var fragment: BaseListingFragment<NewsEntity>

    @Before
    fun setUp() {
        fragment = BaseListingFragment()
    }

    @Test
    fun `lisiting fragment test`() {

    }

}