package com.laaptu.sliding.screen.home.gallery

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.laaptu.sliding.R
import com.laaptu.sliding.utils.getAllOffers
import com.laaptu.sliding.utils.getAllStories
import com.laaptu.sliding.utils.getScreenWidthHeight
import kotlinx.android.synthetic.main.fragment_gallery.*

class GalleryFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val offerAdapter = OfferAdapter(getAllOffers())
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        rvOffers.layoutManager = layoutManager
        rvOffers.addItemDecoration(OfferItemsSpace(resources.getDimensionPixelSize(R.dimen.offer_item_space)))
        rvOffers.adapter = offerAdapter


        val screenWH = getScreenWidthHeight(activity)
        val newHeight = (screenWH[0] * 320) / 700
        val layoutParams = vpGallery.layoutParams
        layoutParams.height = newHeight
        vpGallery.adapter = StoriesAdapter(fragmentManager, getAllStories())
    }
}
