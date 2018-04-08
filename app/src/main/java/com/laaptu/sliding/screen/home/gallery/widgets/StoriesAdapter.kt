package com.laaptu.sliding.screen.home.gallery.widgets

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.laaptu.sliding.model.Story
import com.laaptu.sliding.screen.home.gallery.StoryFragment

class StoriesAdapter(fm: FragmentManager, private val stories: List<Story>) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return StoryFragment.getInstance(stories[position])
    }
    override fun getCount(): Int {
        return stories.size
    }

}
