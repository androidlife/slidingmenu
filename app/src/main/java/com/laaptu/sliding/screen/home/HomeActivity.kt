package com.laaptu.sliding.screen.home

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.laaptu.sliding.R
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.home_content.*

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var selectedIndex: Int = R.id.nav_gallery
    private val SELECTED_INDEX = "SelectedIndex"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)


        val toggle = ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        if (savedInstanceState != null) {
            selectedIndex = savedInstanceState.getInt(SELECTED_INDEX, R.id.nav_gallery)
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
                            R.id.container, GalleryFragment(), fragTag
                    ).commit()
                if (setSelectedMenu)
                    navigationView.setCheckedItem(R.id.nav_gallery)
            }
            R.id.nav_location -> {
                supportActionBar?.title = getString(R.string.places)
                if (supportFragmentManager.findFragmentByTag(fragTag) == null)
                    supportFragmentManager.beginTransaction().replace(
                            R.id.container, PlacesFragment(), fragTag
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
    }


    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (selectedIndex != item.itemId)
            changeSelectedIndex(item.itemId, false)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
