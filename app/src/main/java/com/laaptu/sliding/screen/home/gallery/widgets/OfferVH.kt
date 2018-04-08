package com.laaptu.sliding.screen.home.gallery.widgets

import android.support.v7.widget.RecyclerView
import android.view.View
import au.com.sentia.test.utils.events.RxBus
import com.laaptu.sliding.model.Offer
import com.laaptu.sliding.screen.home.gallery.events.EventOfferClick
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.offer_item.*

class OfferVH(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun setOffer(offer: Offer) {
        ivOffer.setImageResource(offer.imgResId)
        tvTitle.text = offer.title
        ivOffer.setOnClickListener { RxBus.send(EventOfferClick(offer.id, offer.title)) }
    }

}
