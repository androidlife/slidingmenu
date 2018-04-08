package com.laaptu.sliding.screen.home

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.laaptu.sliding.R
import com.laaptu.sliding.screen.home.gallery.GalleryFragment
import com.laaptu.sliding.screen.home.gallery.ViewStateGallery
import com.laaptu.sliding.screen.home.places.PlacesFragment
import com.laaptu.sliding.screen.home.places.ViewStatePlaces
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.home_content.*

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var selectedIndex: Int = R.id.nav_gallery
    private var viewStatePlaces = ViewStatePlaces()
    private var viewStateGallery = ViewStateGallery()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)


        val toggle = ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        if (savedInstanceState != null) {
            selectedIndex = savedInstanceState.getInt(SELECTED_INDEX)
            viewStatePlaces = savedInstanceState.getParcelable(VIEW_STATE_PLACES)
            viewStateGallery = savedInstanceState.getParcelable(VIEW_STATE_GALLERY)
        }
        changeSelectedIndex(selectedIndex, true)
        navigationView.setNavigationItemSelectedListener(this)
    }

    private fun changeSelectedIndex(newIndex: Int, setSelectedMenu: Boolean) {
        val fragTag = newIndex.toString()
        when (newIndex) {
            R.id.nav_gallery -> {
                supportActionBar?.title = getString(R.string.gallery)
                if (supportFragmentManager.findFragmentByTag(fragTag) == null)
                    supportFragmentManager.beginTransaction().replace(
                            R.id.container, GalleryFragment.getInstance(viewStateGallery), fragTag
                    ).commit()
                if (setSelectedMenu)
                    navigationView.setCheckedItem(R.id.nav_gallery)
            }
            R.id.nav_location -> {
                supportActionBar?.title = getString(R.string.places)
                if (supportFragmentManager.findFragmentByTag(fragTag) == null)
                    supportFragmentManager.beginTransaction().replace(
                            R.id.container, PlacesFragment.getInstance(viewStatePlaces), fragTag
                    ).commit()
                if (setSelectedMenu)
                    navigationView.setCheckedItem(R.id.nav_location)
            }
        }
        selectedIndex = newIndex
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt(SELECTED_INDEX, selectedIndex)
        outState?.putParcelable(VIEW_STATE_PLACES, viewStatePlaces)
        outState?.putParcelable(VIEW_STATE_GALLERY, viewStateGallery)
    }


    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (selectedIndex != item.itemId) {
            saveFragmentState(selectedIndex)
            changeSelectedIndex(item.itemId, false)
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onPause() {
        super.onPause()
        saveFragmentState(selectedIndex)
    }

    private fun saveFragmentState(fragId: Int) {
        val fragment = supportFragmentManager.findFragmentByTag(fragId.toString()) ?: return
        when (fragId) {
            R.id.nav_location -> viewStatePlaces = (fragment as PlacesFragment).getViewState().copy()
            R.id.nav_gallery -> viewStateGallery = (fragment as GalleryFragment).getViewState().copy()
        }

    }


}
