package com.laaptu.sliding.network

import com.laaptu.sliding.model.Place
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {
    @GET(URL_SAMPLE)
    fun getPlaces(): Single<List<Place>>
}
