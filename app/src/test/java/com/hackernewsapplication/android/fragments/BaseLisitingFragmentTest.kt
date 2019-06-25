package com.hackernewsapplication.android.fragments

import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.launchFragment
import androidx.lifecycle.Lifecycle
import androidx.test.runner.AndroidJUnit4
import com.hackernewsapplication.android.entity.NewsEntity
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BaseLisitingFragmentTest {
    lateinit var fragment: BaseListingFragment<NewsEntity>
    lateinit var fragmentFactory: FragmentFactory
    @Before
    fun setUp() {
        fragment = BaseListingFragment()
        fragmentFactory = FragmentFactory()
    }

    @Test
    fun `fragment initialisation tests`() {
        val listingFragmentScenario = launchFragment<BaseListingFragment<NewsEntity>>(factory = fragmentFactory)
        listingFragmentScenario.moveToState(Lifecycle.State.RESUMED)
        listingFragmentScenario.onFragment {
            Assert.assertNotNull(it.getAdapterType())
            Assert.assertNotNull(it.adapter())
        }
    }

}