package com.laaptu.sliding.screen.home.gallery

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.laaptu.sliding.R
import com.laaptu.sliding.utils.getAllStories
import com.laaptu.sliding.utils.getScreenWidthHeight
import kotlinx.android.synthetic.main.fragment_gallery.*
import timber.log.Timber

class GalleryFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val screenWH = getScreenWidthHeight(activity)
        Timber.d("Width = %d, height = %d", screenWH[0], screenWH[1])
        val newHeight = (screenWH[0] * 320) / 700
        val layoutParams = vpGallery.layoutParams
        layoutParams.height = newHeight
        vpGallery.adapter = StoriesAdapter(fragmentManager, getAllStories())
    }
}
