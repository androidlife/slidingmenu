package com.laaptu.sliding.screen.home.places

import com.laaptu.sliding.model.Error
import com.laaptu.sliding.model.Place

interface PlacesContract {
    interface View {
        enum class ViewState {
            Empty, Error, Loaded
        }

        fun showProgress(show: Boolean)
        fun showError(show: Boolean)
        fun getViewState(): ViewState
        fun setViewState(viewViewState: ViewState)
        fun isConnectedToNetwork(): Boolean
        fun getSelectedIndex(): Int
        fun onItemSelected(index: Int)
        fun setData(places: List<Place>)
        fun getData(): List<Place>
    }

    interface Presenter {
        fun start(start: Boolean)
        fun retry()
        fun onItemSelected(index: Int)
    }

    interface Model {
        fun cancel(cancel: Boolean)
        fun fetchPlaces(listener: OnPlaceFetchListener)
    }


    interface OnPlaceFetchListener {
        fun onFetchSuccess(places: List<Place>)
        fun onFetchFailure(error: Error)
    }
}
