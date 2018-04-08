package com.laaptu.sliding.screen.home.gallery

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.laaptu.sliding.R
import com.laaptu.sliding.model.Story
import com.laaptu.sliding.screen.home.DEAL_DATA
import com.laaptu.sliding.screen.home.VIEW_STATE_GALLERY
import com.laaptu.sliding.screen.home.gallery.widgets.OfferAdapter
import com.laaptu.sliding.screen.home.gallery.widgets.OfferItemsSpace
import com.laaptu.sliding.screen.home.gallery.widgets.StoriesAdapter
import com.laaptu.sliding.utils.getAllOffers
import com.laaptu.sliding.utils.getAllStories
import com.laaptu.sliding.utils.getScreenWidthHeight
import com.laaptu.sliding.widgets.ColorButton
import kotlinx.android.synthetic.main.fragment_gallery.*

class GalleryFragment : Fragment() {

    private lateinit var viewStateGallery: ViewStateGallery
    private lateinit var bgButtons: List<ColorButton>

    companion object {
        fun getInstance(viewStateGallery: ViewStateGallery): GalleryFragment {
            val galleryFragment = GalleryFragment()
            val params = Bundle()
            params.putParcelable(VIEW_STATE_GALLERY, viewStateGallery)
            galleryFragment.arguments = params
            return galleryFragment
        }

        fun getDeal(params: Bundle?): ViewStateGallery {
            if (params != null && params.containsKey(VIEW_STATE_GALLERY))
                return params.getParcelable(VIEW_STATE_GALLERY)
            return ViewStateGallery()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        viewStateGallery = getDeal(arguments)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        bgButtons = listOf(btnRed, btnGreen, btnBlue)

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

    private fun changeBackground(btnColorIndex: Int) {
        val colorButton = bgButtons[btnColorIndex]
        colorButton.setChecked(true)
        if (viewStateGallery.selectedColorIndex != NOT_SELECTED)
            bgButtons[btnColorIndex].setChecked(false)
        parentLayout.setBackgroundColor(colorButton.getSelectedTextColor())
        viewStateGallery.selectedColorIndex = btnColorIndex
    }
}
