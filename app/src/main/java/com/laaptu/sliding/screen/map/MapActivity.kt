package com.laaptu.sliding.screen.map

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.laaptu.sliding.R
import com.laaptu.sliding.model.Location


class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    companion object {
        val EXTRAS_LOCATION = "Location"
        val EXTRAS_LOCATION_NAME = "LocationName"
        fun getLaunchingIntent(context: Context, location: Location, locationName: String): Intent {
            val intent = Intent(context, MapActivity::class.java)
            addLocationIntent(intent, location, locationName)
            return intent
        }

        fun addLocationIntent(intent: Intent, location: Location, locationName: String) {
            intent.putExtra(EXTRAS_LOCATION, location)
            intent.putExtra(EXTRAS_LOCATION_NAME, locationName)
        }

        private fun getLocation(intent: Intent): Location? {
            if (intent.hasExtra(EXTRAS_LOCATION))
                return intent.getParcelableExtra(EXTRAS_LOCATION)
            return null
        }

        private fun getLocationName(intent: Intent): String? {
            if (intent.hasExtra(EXTRAS_LOCATION_NAME))
                return intent.getStringExtra(EXTRAS_LOCATION_NAME)
            return null
        }
    }

    val mapFragment by lazy {
        supportFragmentManager
                .findFragmentById(R.id.mapFragment) as SupportMapFragment
    }
    private var location: Location? = null
    private var locationName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        location = getLocation(intent)
        locationName = getLocationName(intent)
        if (location == null) {
            finish()
            return
        }
        initActionBar()
        mapFragment.getMapAsync(this)
    }

    private fun initActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = locationName
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        val latLng = LatLng(location!!.latitude, location!!.longitude)
        googleMap?.addMarker(MarkerOptions().position(latLng).title(locationName))
        googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
    }

}