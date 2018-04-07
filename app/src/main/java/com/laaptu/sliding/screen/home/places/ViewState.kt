package com.laaptu.sliding.screen.home.places

import android.os.Parcelable
import com.laaptu.sliding.model.Place
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ViewState(var state: Int = StateEmpty,
                     var selectedIndex: Int = 0,
                     var data: List<Place>? = null) : Parcelable {
    companion object {
        val StateEmpty: Int = 0x1
        val StateError: Int = 0x2
        val StateLoaded: Int = 0x3
    }

}
