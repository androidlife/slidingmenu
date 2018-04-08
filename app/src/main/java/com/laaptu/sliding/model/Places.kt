package com.laaptu.sliding.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(@SerializedName("latitude") val latitude: Double,
                    @SerializedName("longitude") val longitude: Double) : Parcelable

@Parcelize
data class FromCentral(@SerializedName("car") val byCar: String =" ",
                       @SerializedName("train") val byTrain: String = " ") : Parcelable

@Parcelize
data class Place(@SerializedName("id") val id: Long,
                 @SerializedName("name") val locationName: String,
                 @SerializedName("location") val location: Location? = null,
                 @SerializedName("fromcentral") val fromCentral: FromCentral? = null) : Parcelable {
    override fun toString(): String {
        return locationName
    }
}

const val EMPTY_PLACE_ID = -1L
const val EMPTY_PLACE_NAME = "No Places Found"