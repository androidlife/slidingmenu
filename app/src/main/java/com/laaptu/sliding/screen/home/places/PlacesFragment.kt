package com.laaptu.sliding.screen.home.places

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import au.com.sentia.test.network.provider.ApiManager
import com.laaptu.sliding.R
import com.laaptu.sliding.model.FromCentral
import com.laaptu.sliding.model.Place
import com.laaptu.sliding.screen.home.VIEW_STATE_PLACES
import com.laaptu.sliding.screen.map.MapActivity
import kotlinx.android.synthetic.main.fragment_places.*
import android.view.View as View_

class PlacesFragment : Fragment(), PlacesContract.View, AdapterView.OnItemSelectedListener {

    companion object {
        fun getInstance(viewStatePlaces: ViewStatePlaces): PlacesFragment {
            val placesFragment = PlacesFragment()
            val params = Bundle()
            params.putParcelable(VIEW_STATE_PLACES, viewStatePlaces)
            placesFragment.arguments = params
            return placesFragment
        }

        fun getViewState(bundle: Bundle?): ViewStatePlaces {
            if (bundle != null && bundle.containsKey(VIEW_STATE_PLACES))
                return bundle.getParcelable(VIEW_STATE_PLACES)
            return ViewStatePlaces()
        }
    }


    private var loadedViews: List<View> = ArrayList()
    private var viewState: ViewStatePlaces = ViewStatePlaces()
    private lateinit var presenter: PlacesContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        presenter = PlacesPresenter(this, PlacesModel(ApiManager.apiService))
        viewState = getViewState(arguments)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_places, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadedViews = listOf(spinnerPlaces, dividerFirst, txtTransportInfo, dividerSecond, btnLocation)
        spinnerPlaces.onItemSelectedListener = this
        btnLocation.setOnClickListener { presenter.showMapIntent() }
        txtInfo.setOnClickListener { presenter.retry() }
    }


    override fun onStart() {
        super.onStart()
        btnLocation.text = getString(R.string.icon_location).plus("  ")
                .plus(getString(R.string.location))
        presenter.start(true)
    }

    override fun onStop() {
        super.onStop()
        presenter.start(false)
    }

    override fun showProgress(show: Boolean) {
        pb.visibility = when (show) {
            true -> View.VISIBLE
            false -> View.GONE
        }
    }

    override fun showError(show: Boolean) {
        txtInfo.visibility = when (show) {
            true -> View.VISIBLE
            false -> View.GONE
        }
    }

    override fun showLoadedViews(show: Boolean) {
        val iterator = loadedViews.listIterator()
        for (view in iterator)
            view.visibility = when (show) {
                true -> View.VISIBLE
                false -> View.GONE
            }
    }

    override fun showInfo(info: String) {
        Toast.makeText(context, info, Toast.LENGTH_SHORT).show()
    }

    override fun getViewState(): ViewStatePlaces {
        return viewState
    }

    override fun isConnectedToNetwork(): Boolean {
        return com.laaptu.sliding.utils.isConnectedToNetwork(context)
    }

    override fun onPlaceSelected(fromCentral: FromCentral?) {
        if (fromCentral == null)
            return
        txtTransportInfo.text = getString(R.string.mode_of_transport).plus("\n\n")
                .plus(getString(R.string.icon_car)).plus("  ").plus(fromCentral.byCar)
                .plus("\n\n")
                .plus(getString(R.string.icon_train)).plus("  ").plus(fromCentral.byTrain)
                .plus("\n")

    }

    override fun setData(places: List<Place>) {
        val adapter = ArrayAdapter<Place>(context, android.R.layout.simple_spinner_item, places)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPlaces.adapter = adapter
    }

    override fun showMap(place: Place) {
        startActivity(MapActivity.getLaunchingIntent(context, place.location!!,
                place.locationName))
    }


    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        presenter.onItemSelected(position)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun selectItemAt(index: Int) {
        spinnerPlaces.setSelection(index)
    }


}