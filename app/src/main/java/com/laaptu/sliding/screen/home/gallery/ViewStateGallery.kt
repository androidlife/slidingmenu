package com.laaptu.sliding.screen.home.gallery

import android.os.Parcelable
import com.laaptu.sliding.model.Place
import com.laaptu.sliding.screen.home.places.ViewStatePlaces.Companion.StateEmpty
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ViewStateGallery(var selectedOfferIndex: Int = NOT_SELECTED,
                            var selectedStoryIndex: Int = NOT_SELECTED,
                            var selectedColorIndex: Int = NOT_SELECTED) : Parcelable

const val NOT_SELECTED = -1