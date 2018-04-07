package com.laaptu.sliding.screen.home.places

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.laaptu.sliding.R
import com.laaptu.sliding.model.Place
import kotlinx.android.synthetic.main.fragment_places.*

class PlacesFragment : Fragment(), PlacesContract.View {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_places, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btnLocation.text = getString(R.string.icon_location).plus("  ")
                .plus(getString(R.string.location))
    }


    override fun showProgress(show: Boolean) {
    }

    override fun showError(show: Boolean) {
    }

    override fun getViewState(): PlacesContract.View.ViewState {
        return PlacesContract.View.ViewState.Empty
    }

    override fun setViewState(viewViewState: PlacesContract.View.ViewState) {
    }

    override fun isConnectedToNetwork(): Boolean {
        return false
    }

    override fun getSelectedIndex(): Int {
        return 0
    }

    override fun onItemSelected(index: Int) {
    }

    override fun setData(places: List<Place>) {
    }

    override fun getData(): List<Place> {
        return ArrayList<Place>()
    }

}