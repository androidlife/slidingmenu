package com.laaptu.sliding.screen

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingPolicies
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.laaptu.sliding.R
import com.laaptu.sliding.screen.home.HomeActivity
import com.laaptu.sliding.utils.CustomIdlingResource
import com.laaptu.sliding.utils.getScreenWidthHeight
import com.laaptu.sliding.utils.nestedScrollVertically
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {
    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule<HomeActivity>(HomeActivity::class.java)

    private val TIMEOUT_ESPRESSO = 60
    private val TIMEOUT_LOAD: Long = 5
    private val TIMEOUT_UNIT = TimeUnit.SECONDS

    private var idlingResource: CustomIdlingResource? = null

    @Before
    fun setUp() {
        IdlingPolicies.setMasterPolicyTimeout(TIMEOUT_ESPRESSO.toLong(), TIMEOUT_UNIT)
    }

    @After
    fun onTestComplete() {
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
        onView(withId(id)).check(matches(isDisplayed()))
        onView(withId(id)).perform(click())
    }

    private fun initIdlingResourceWithTimeOut(timeOut: Long, timeUnit: TimeUnit) {
        onTestComplete()
        idlingResource = CustomIdlingResource()
        IdlingRegistry.getInstance().register(idlingResource)
        idlingResource?.startCountdown(timeOut, timeUnit)
    }
}
