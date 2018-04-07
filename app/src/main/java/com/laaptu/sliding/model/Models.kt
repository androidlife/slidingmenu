package com.laaptu.sliding.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(@SerializedName("latitude") val latitude: Double,
                    @SerializedName("longitude") val longitude: Double) : Parcelable

@Parcelize
data class FromCentral(@SerializedName("car") val byCar: String,
                       @SerializedName("train") val byTrain: String = "") : Parcelable

@Parcelize
data class Place(@SerializedName("id") val id: Long,
                 @SerializedName("name") val locationName: String,
                 @SerializedName("location") val location: Location,
                 @SerializedName("fromCentral") val fromCentral: FromCentral) : Parcelable