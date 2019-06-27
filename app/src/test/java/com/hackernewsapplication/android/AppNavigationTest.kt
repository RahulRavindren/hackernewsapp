package com.hackernewsapplication.android

import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import com.hackernewsapplication.android.fragments.DetailFragment
import com.hackernewsapplication.android.fragments.ListingFragment
import com.hackernewsapplication.android.utils.AppNavigation
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * @Author rahulravindran
 */
@RunWith(MockitoJUnitRunner::class)
class AppNavigationTest {
    @Mock
    lateinit var supportFragmentManager: FragmentManager
    @Mock
    lateinit var container: FrameLayout
    @Mock
    lateinit var transaction: FragmentTransaction

    lateinit var appNavigation: AppNavigation

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        appNavigation = AppNavigation(supportFragmentManager, container)

    }

    @Test
    fun `init case for app nav`() {
        appNavigation.init(null)
        appNavigation.setNavigationChangeListener(object : AppNavigation.NavigationChangeListener {
            override fun onChangeDestination(
                controller: NavController,
                destination: NavDestination,
                arguments: Bundle?
            ) {
                Assert.assertThat(
                    appNavigation.getCurrentVisibleFragment(),
                    CoreMatchers.instanceOf(ListingFragment::class.java)
                )
            }
        })
    }

    @Test
    fun `move to detail fragment`() {
        appNavigation.init(null)
        appNavigation.navigate(R.id.news_detail_fragment, Bundle())
        appNavigation.setNavigationChangeListener(object : AppNavigation.NavigationChangeListener {
            override fun onChangeDestination(
                controller: NavController,
                destination: NavDestination,
                arguments: Bundle?
            ) {
                Assert.assertThat(
                    appNavigation.getCurrentVisibleFragment(),
                    CoreMatchers.instanceOf(DetailFragment::class.java)
                )
            }
        })
    }

    @Test
    fun `move to detail fragment without bundle`() {
        appNavigation.init(null)
        appNavigation.navigate(R.id.news_detail_fragment)
        appNavigation.setNavigationChangeListener(object : AppNavigation.NavigationChangeListener {
            override fun onChangeDestination(
                controller: NavController,
                destination: NavDestination,
                arguments: Bundle?
            ) {
                Assert.assertThat(
                    appNavigation.getCurrentVisibleFragment(),
                    CoreMatchers.instanceOf(DetailFragment::class.java)
                )
            }
        })
    }


}