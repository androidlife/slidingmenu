package com.laaptu.sliding.utils

import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.matcher.ViewMatchers
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v4.widget.NestedScrollView
import android.view.View
import org.hamcrest.Matcher
import org.hamcrest.Matchers


fun nestedScrollVertically(scrollY: Int): ViewAction {
    return object : ViewAction {
        override fun getDescription(): String {
            return "Performing nested scrolling"
        }

        override fun getConstraints(): Matcher<View> {
            return Matchers.allOf<View>(ViewMatchers.isAssignableFrom(NestedScrollView::class.java), ViewMatchers.isDisplayed())

        }

        override fun perform(uiController: UiController?, view: View?) {
            val nestedScrollView = view as NestedScrollView
            nestedScrollView.scrollTo(0, scrollY)
        }
    }
}

fun openSlidingDrawer(): ViewAction {
    return object : ViewAction {
        override fun getDescription(): String {
            return "Performing opening of sliding drawer"
        }

        override fun getConstraints(): Matcher<View> {
            return Matchers.allOf<View>(ViewMatchers.isAssignableFrom(DrawerLayout::class.java), ViewMatchers.isDisplayed())

        }

        override fun perform(uiController: UiController?, view: View?) {
            val drawerLayout = view as DrawerLayout
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }
}