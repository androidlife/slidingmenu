package com.laaptu.sliding.screen

import android.content.Intent
import android.support.test.espresso.IdlingPolicies
import android.support.test.espresso.IdlingRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.laaptu.sliding.model.Location
import com.laaptu.sliding.screen.map.MapActivity
import com.laaptu.sliding.utils.CustomIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class MapActivityTest {
    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule<MapActivity>(MapActivity::class.java,
            true, false)

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
    fun mapLoadSuccess() {
        val location = Location(-33.6883393, 151.1021816)
        val intent = Intent()
        MapActivity.addLocationIntent(intent, location, "Asquith")
        activityTestRule.launchActivity(intent)
        idlingResource = CustomIdlingResource()
        IdlingRegistry.getInstance().register(idlingResource)
        idlingResource?.startCountdown(TIMEOUT_LOAD, TIMEOUT_UNIT)
    }
}
