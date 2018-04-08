package com.laaptu.sliding.screen.home.gallery

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import au.com.sentia.test.utils.events.RxBus
import com.laaptu.sliding.R
import com.laaptu.sliding.screen.home.VIEW_STATE_GALLERY
import com.laaptu.sliding.screen.home.gallery.events.EventOfferClick
import com.laaptu.sliding.screen.home.gallery.widgets.OfferAdapter
import com.laaptu.sliding.screen.home.gallery.widgets.OfferItemsSpace
import com.laaptu.sliding.screen.home.gallery.widgets.StoriesAdapter
import com.laaptu.sliding.utils.getAllOffers
import com.laaptu.sliding.utils.getAllStories
import com.laaptu.sliding.utils.getScreenWidthHeight
import com.laaptu.sliding.widgets.ColorButton
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_gallery.*

class GalleryFragment : Fragment(), ViewPager.OnPageChangeListener {


    private lateinit var viewStateGallery: ViewStateGallery
    private lateinit var bgButtons: List<ColorButton>
    private lateinit var clickListener: Disposable
    private val offers = getAllOffers()

    companion object {
        fun getInstance(viewStateGallery: ViewStateGallery): GalleryFragment {
            val galleryFragment = GalleryFragment()
            val params = Bundle()
            params.putParcelable(VIEW_STATE_GALLERY, viewStateGallery)
            galleryFragment.arguments = params
            return galleryFragment
        }

        fun getViewState(params: Bundle?): ViewStateGallery {
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
        viewStateGallery = getViewState(arguments)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initViews()
        changeBackground(viewStateGallery.selectedColorIndex)
        if (viewStateGallery.selectedStoryIndex != NOT_SELECTED && viewStateGallery.selectedStoryIndex != vpGallery.currentItem)
            vpGallery.currentItem = viewStateGallery.selectedStoryIndex
        onOfferSelected(viewStateGallery.selectedOfferIndex)

    }

    private fun initViews() {
        bgButtons = listOf(btnRed, btnGreen, btnBlue)
        bgButtons.forEachIndexed { index, btn ->
            btn.setOnClickListener {
                if (index != viewStateGallery.selectedColorIndex)
                    changeBackground(index)
            }
        }

        val offerAdapter = OfferAdapter(offers)
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
        vpGallery.addOnPageChangeListener(this)
    }


    private fun changeBackground(btnColorIndex: Int) {
        val colorButton = getColorButton(btnColorIndex) ?: return
        val prevSelectedButton = getColorButton(viewStateGallery.selectedColorIndex)
        if (prevSelectedButton != null && viewStateGallery.selectedColorIndex != btnColorIndex)
            prevSelectedButton.setChecked(false)
        colorButton.setChecked(true)
        parentLayout.setBackgroundColor(colorButton.getSelectedTextColor())
        viewStateGallery.selectedColorIndex = btnColorIndex
    }

    private fun getColorButton(index: Int): ColorButton? {
        if (index >= 0 && index < bgButtons.size)
            return bgButtons[index]
        return null
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        viewStateGallery.selectedStoryIndex = position
    }

    fun getViewState(): ViewStateGallery {
        return viewStateGallery
    }

    override fun onStart() {
        super.onStart()
        listenToClickEvents(true)
    }

    override fun onStop() {
        super.onStop()
        listenToClickEvents(false)
    }

    private fun listenToClickEvents(listen: Boolean) {
        when (listen) {
            true -> clickListener = RxBus.toObservable().subscribe { event -> onClickEvent(event) }
            false -> if (clickListener != null) clickListener.dispose()
        }
    }

    private fun onClickEvent(event: Any) {
        if (event is EventOfferClick)
            onOfferSelected(event.index)

    }

    private fun onOfferSelected(index: Int) {
        if (index < 0 || index >= offers.size)
            return
        tvDetail.text = offers[index].title
        viewStateGallery.selectedOfferIndex = index
        rvOffers.scrollToPosition(index)
    }
}
