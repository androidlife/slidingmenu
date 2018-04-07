package com.laaptu.sliding.screen.home.places

import com.laaptu.sliding.model.Error
import com.laaptu.sliding.model.Place

class PlacesPresenter(val view: PlacesContract.View, val model: PlacesContract.Model) : PlacesContract.Presenter {
    override fun start(start: Boolean) {
        when (start) {
            true -> initViewState(view.getViewState())
            false -> model.cancel(true)
        }
    }

    private fun initViewState(viewState: ViewState) {
        when (viewState.state) {
            ViewState.StateEmpty -> fetchPlaces()
        }
    }

    private fun fetchPlaces() {
        if (view.getViewState().state != ViewState.StateEmpty)
            return
        if (!view.isConnectedToNetwork()) {
            setViewError("No Internet Connection")
            return
        }
        model.cancel(false)
        view.showError(false)
        view.showLoadedViews(false)
        view.showProgress(true)
        model.fetchPlaces(object : PlacesContract.OnPlaceFetchListener {
            override fun onFetchSuccess(places: List<Place>) {
                onPlacesFetched(places)
            }

            override fun onFetchFailure(error: Error) {
                setViewError("Error fetching places")
            }

        })
    }

    override fun retry() {
        setViewState(ViewState.StateEmpty)
        fetchPlaces()
    }

    fun setViewState(viewState: Int) {
        view.getViewState().state = viewState
    }

    override fun onItemSelected(index: Int) {
    }

    fun setViewError(errorMsg: String) {
        view.showProgress(false)
        view.showError(true)
        view.showInfo(errorMsg)
        setViewState(ViewState.StateError)
    }

    fun onPlacesFetched(places: List<Place>) {
        view.showProgress(false)
        if (places.isEmpty()) {
            view.showInfo("No places found")
        }
        view.getViewState().data = places
        view.setData(places)
        view.showLoadedViews(true)
        onItemSelected(0)
        setViewState(ViewState.StateLoaded)

    }

}
