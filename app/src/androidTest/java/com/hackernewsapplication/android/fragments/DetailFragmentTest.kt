package com.hackernewsapplication.android.fragments

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.FragmentScenario.launchInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hackernewsapplication.android.entity.NewsEntity
import com.hackernewsapplication.common.C
import com.hackernewsapplication.common.utils.bundleOf
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @Author rahulravindran
 */
@RunWith(AndroidJUnit4::class)
class DetailFragmentTest {
    var scenario: FragmentScenario<DetailFragment>? = null

    @Before
    fun setUp() {
        scenario = launchInContainer(
            DetailFragment::class.java, bundleOf(
                C.NEWS_ENTITY to NewsEntity(id = 8863)
            )
        )
    }

    @Test
    fun received_args_evaluation() {
        scenario?.onFragment {
            val entity = it.arguments?.getParcelable(C.NEWS_ENTITY) as? NewsEntity
            Assert.assertNotNull(entity)
            Assert.assertEquals(8863, entity?.id)
        }
    }
}
