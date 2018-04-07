package com.laaptu.sliding.screen.home.places

import com.laaptu.sliding.model.EMPTY_PLACE_ID
import com.laaptu.sliding.model.EMPTY_PLACE_NAME
import com.laaptu.sliding.model.Error
import com.laaptu.sliding.model.Place
import com.laaptu.sliding.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PlacesModel(private val apiService: ApiService) : PlacesContract.Model {


    private var cancel: Boolean = false
    private var apiCallback: Disposable? = null
    override fun cancel(cancel: Boolean) {
        this.cancel = cancel
        if (this.cancel)
            apiCallback?.dispose()
    }

    override fun fetchPlaces(listener: PlacesContract.OnPlaceFetchListener) {
        apiCallback = apiService.getPlaces().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ places -> onSuccess(places, listener) },
                        { e -> onFailure(Error(Error.Type.Fetch), listener) })
    }

    private fun onSuccess(places: List<Place>, listener: PlacesContract.OnPlaceFetchListener) {
        if (cancel)
            return
        listener.onFetchSuccess(places)
    }

    private fun onFailure(error: Error, listener: PlacesContract.OnPlaceFetchListener) {
        if (cancel)
            return
        listener.onFetchFailure(error)
    }

    override fun getEmptyPlace(): Place {
        return Place(EMPTY_PLACE_ID, EMPTY_PLACE_NAME)
    }

}
