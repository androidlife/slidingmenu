package com.laaptu.sliding.screen.home.places

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.laaptu.sliding.R
import com.laaptu.sliding.model.Place
import kotlinx.android.synthetic.main.fragment_places.*
import android.view.View as View_

class PlacesFragment : Fragment(), PlacesContract.View {


    private var loadedViews: List<View> = ArrayList<View>()
    private var viewState: ViewState = ViewState()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_places, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadedViews = listOf(spinnerPlaces, dividerFirst, txtTransportInfo, dividerSecond, btnLocation)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btnLocation.text = getString(R.string.icon_location).plus("  ")
                .plus(getString(R.string.location))
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


    override fun getViewState(): ViewState {
        return viewState
    }

    override fun setViewState(viewState: ViewState) {
    }

    override fun isConnectedToNetwork(): Boolean {
        return false
    }

    override fun onItemSelected(index: Int) {
    }

    override fun setData(places: List<Place>) {
    }


}