package com.laaptu.sliding.screen.home.gallery

import android.support.v7.widget.RecyclerView
import android.view.View
import com.laaptu.sliding.model.Offer
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.offer_item.*

class OfferVH(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun setOffer(offer: Offer) {
        ivOffer.setImageResource(offer.imgResId)
        tvTitle.text = offer.title
    }

}
