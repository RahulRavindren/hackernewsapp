package com.hackernewsapplication.android.fragments

import android.view.View
import androidx.fragment.app.testing.FragmentScenario
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.runner.AndroidJUnit4
import com.hackernewsapplication.AbsListViewMatcher
import com.hackernewsapplication.android.R
import com.hackernewsapplication.android.view.listing.viewholder.NewsListingItemViewHolder
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @Author rahulravindran
 */
@RunWith(AndroidJUnit4::class)
class ListingFragmentTest {
    var scenario: FragmentScenario<ListingFragment>? = null

    @Test
    fun loading_indicator_confirmation() {
        with(FragmentScenario.launchInContainer(ListingFragment::class.java)) {
            onFragment {
                IdlingRegistry.getInstance().register(it.idlingResource())
            }
            onView(withId(R.id.refresh_list)).check(matches(isRefreshing()))
        }

    }

    @Test
    fun click_item_open_detail_frag() {
        with(FragmentScenario.launchInContainer(ListingFragment::class.java)) {
            onFragment {
                IdlingRegistry.getInstance().register(it.idlingResource())
                it.idlingResource()?.increment()
            }
            onView(withId(R.id.base_listing)).check(matches(isDisplayed()));
            onView(withId(R.id.base_listing))
                .perform(RecyclerViewActions.actionOnItemAtPosition<NewsListingItemViewHolder>(0, click()))
        }

    }

    @Test
    fun list_has_items_on_view() {
        with(FragmentScenario.launchInContainer(ListingFragment::class.java)) {
            onFragment {
                IdlingRegistry.getInstance().register(it.idlingResource())
                it.idlingResource()?.increment()
            }
            onView(withId(R.id.base_listing)).check(matches(AbsListViewMatcher()))
        }
    }

    @After
    fun tearDown() {
        scenario?.onFragment {
            IdlingRegistry.getInstance().unregister(it.idlingResource())
        }
    }

    //custom matcher for confirming swiperefresh loader
    fun isRefreshing(): Matcher<View> {
        return object : BoundedMatcher<View, SwipeRefreshLayout>(
            SwipeRefreshLayout::class.java
        ) {

            override fun describeTo(description: Description) {
                description.appendText("is refreshing")
            }

            override fun matchesSafely(view: SwipeRefreshLayout): Boolean {
                return view.isRefreshing
            }
        }
    }
}

