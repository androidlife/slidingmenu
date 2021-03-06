package com.laaptu.sliding.screen.home.places

import com.laaptu.sliding.model.Error
import com.laaptu.sliding.model.FromCentral
import com.laaptu.sliding.model.Place

interface PlacesContract {
    interface View {

        fun showProgress(show: Boolean)
        fun showLoadedViews(show: Boolean)
        fun showError(show: Boolean)
        fun getViewState(): ViewStatePlaces
        fun isConnectedToNetwork(): Boolean
        fun setData(places: List<Place>)
        fun showInfo(info: String)
        fun onPlaceSelected(fromCentral: FromCentral?)
        fun showMap(place: Place)
        fun selectItemAt(index: Int)
    }

    interface Presenter {
        fun start(start: Boolean)
        fun retry()
        fun onItemSelected(index: Int)
        fun showMapIntent()

    }

    interface Model {
        fun cancel(cancel: Boolean)
        fun fetchPlaces(listener: OnPlaceFetchListener)
        fun getEmptyPlace(): Place
    }


    interface OnPlaceFetchListener {
        fun onFetchSuccess(places: List<Place>)
        fun onFetchFailure(error: Error)
    }
}
