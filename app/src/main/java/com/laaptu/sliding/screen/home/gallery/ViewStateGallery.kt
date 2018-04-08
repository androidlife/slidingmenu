package com.laaptu.sliding.screen.home.gallery

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ViewStateGallery(var selectedOfferIndex: Int = NOT_SELECTED,
                            var selectedStoryIndex: Int = NOT_SELECTED,
                            var selectedColorIndex: Int = NOT_SELECTED) : Parcelable

const val NOT_SELECTED = -1