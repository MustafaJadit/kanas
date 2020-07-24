package com.kodyuzz.kanas.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

object RVMatcher {

    fun atPositionOnView(
        position: Int, itemMatcher: Matcher<View>,
        targetViewId: Int
    ): Matcher<View> {

        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun matchesSafely(item: RecyclerView): Boolean {
                val viewHolder = item.findViewHolderForAdapterPosition(position)
                val targetView = viewHolder!!.itemView.findViewById<View>(targetViewId)
                return itemMatcher.matches(targetView)

            }

            override fun describeTo(description: Description) {
                description.appendText("has view id $itemMatcher at position $position")
            }

        }
    }
}