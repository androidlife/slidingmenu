package com.laaptu.sliding

import au.com.sentia.test.network.provider.ApiManager
import org.junit.Assert.assertTrue
import org.junit.Test

class SimpleFetchTest {
    @Test
    fun `fetching places assuming all values will be positive`() {
        val places = ApiManager.apiService.getPlaces().blockingGet()
        assertTrue(places != null && places.size == 3 && places[0].id == 1L)
        val place = places[0]
        assertTrue(place.locationName.equals("Blue Mountains")
                && place.location.latitude == -33.7181)
    }
}
