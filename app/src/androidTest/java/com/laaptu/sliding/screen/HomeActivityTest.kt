package com.laaptu.sliding.screen

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingPolicies
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.laaptu.sliding.R
import com.laaptu.sliding.screen.home.HomeActivity
import com.laaptu.sliding.utils.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

private const val TIMEOUT_ESPRESSO = 60L
private val TIMEOUT_UNIT = TimeUnit.SECONDS

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {
    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule<HomeActivity>(HomeActivity::class.java)


    private var idlingResource: CustomIdlingResource? = null

    @Before
    fun setUp() {
        IdlingPolicies.setMasterPolicyTimeout(TIMEOUT_ESPRESSO, TIMEOUT_UNIT)
    }

    @After
    fun clearIdlingResource() {
        if (idlingResource != null) {
            IdlingRegistry.getInstance().unregister(idlingResource)
        }
    }

    @Test
    fun placesBgColorTest() {
        val screenWH = getScreenWidthHeight(activityTestRule.activity)
        onView(withId(R.id.nsvParent)).perform(nestedScrollVertically(screenWH[1] * 3))
        checkDisplayAndClick(R.id.btnRed)
        checkDisplayAndClick(R.id.btnGreen)
        checkDisplayAndClick(R.id.btnBlue)
    }

    private fun checkDisplayAndClick(id: Int) {
        onView(withId(id)).check(matches(isDisplayed())).perform(click())
    }

    @Test
    fun navigateToPlaces() {
        onView(withId(R.id.drawerLayout)).perform(openSlidingDrawer())
        val navigationTitle = activityTestRule.activity.getString(R.string.navigation)
        initIdlingResourceWithTimeOut(500, TimeUnit.MILLISECONDS)
        onView(withText(navigationTitle)).check(matches(isDisplayed())).perform(click())
        initIdlingResourceWithTimeOut(2, TimeUnit.SECONDS)
        onView(withId(R.id.btnLocation)).check(matches(isDisplayed()))
    }

    @Test
    fun navigateToMap() {
        navigateToPlaces()
        onView(withId(R.id.btnLocation)).perform(click())
        initIdlingResourceWithTimeOut(2, TimeUnit.SECONDS)
        onView(withContentDescription("Google Map")).check(matches(isDisplayed()))
    }

    @Test
    fun validatePlacesSelection() {
        navigateToPlaces()
        val text = selectItemOfSpinner(R.id.spinnerPlaces, 2)
        onView(withText(text)).check(matches(isDisplayed()))
    }

    private fun initIdlingResourceWithTimeOut(timeOut: Long, timeUnit: TimeUnit) {
        clearIdlingResource()
        idlingResource = CustomIdlingResource()
        IdlingRegistry.getInstance().register(idlingResource)
        idlingResource?.startCountdown(timeOut, timeUnit)
    }
}
