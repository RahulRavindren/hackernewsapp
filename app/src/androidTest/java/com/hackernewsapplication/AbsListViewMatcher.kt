package com.hackernewsapplication

import android.view.View
import androidx.test.espresso.matcher.BoundedMatcher
import com.hackernewsapplication.common.customviews.CustomVerticalRecyclerView
import org.hamcrest.Description

class AbsListViewMatcher : BoundedMatcher<View, CustomVerticalRecyclerView>(CustomVerticalRecyclerView::class.java) {

    override fun describeTo(description: Description?) {
        description?.appendText("checks for items in recyclerview")
    }

    override fun matchesSafely(item: CustomVerticalRecyclerView?): Boolean = run {
        val adapterCount = item?.adapter?.itemCount ?: -1
        adapterCount > 0
    }


}
