package com.laaptu.sliding.screen.home.places

import com.laaptu.sliding.model.EMPTY_PLACE_ID
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
        view.showLoadedViews(false)
        when (viewState.state) {
            ViewState.StateEmpty -> fetchPlaces()
            ViewState.StateLoaded -> {
                onPlacesFetched(view.getViewState().data)
                view.selectItemAt(view.getViewState().selectedIndex)
            }
            ViewState.StateError -> setViewError(null)
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
                onItemSelected(0)
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

    private fun setViewState(viewState: Int) {
        view.getViewState().state = viewState
    }

    override fun onItemSelected(index: Int) {
        view.getViewState().selectedIndex = index
        val selectedPlace = view.getViewState().data?.get(index)
        if (selectedPlace?.id == EMPTY_PLACE_ID)
            return
        view.onPlaceSelected(selectedPlace?.fromCentral)
    }

    override fun showMapIntent() {
        val selectedPlace = view.getViewState().data?.get(view.getViewState().selectedIndex)
        if (selectedPlace != null)
            view.showMap(selectedPlace)
    }

    fun setViewError(errorMsg: String?) {
        view.showProgress(false)
        view.showError(true)
        if (errorMsg != null)
            view.showInfo(errorMsg)
        setViewState(ViewState.StateError)
    }

    private fun onPlacesFetched(places: List<Place>?) {
        view.showProgress(false)
        view.showError(false)
        if (places!!.isEmpty()) {
            view.showInfo("No places found")
            val empty = listOf<Place>(model.getEmptyPlace())
            view.getViewState().data = empty
        } else {
            view.getViewState().data = places
            view.showLoadedViews(true)
        }
        view.setData(places!!)
        setViewState(ViewState.StateLoaded)
    }


}
