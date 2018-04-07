package com.laaptu.sliding.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(val latitude: Double, val longitude: Double, val locationName: String):Parcelable