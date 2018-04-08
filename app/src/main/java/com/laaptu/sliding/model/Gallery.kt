package com.laaptu.sliding.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
open class Offer(val id: Int, val imgResId: Int, val title: String) : Parcelable

@Parcelize
open class Story(val id: Int, val imgResId: Int, val title: String) : Parcelable

