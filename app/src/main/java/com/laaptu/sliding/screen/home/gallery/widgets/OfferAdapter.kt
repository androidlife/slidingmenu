package com.laaptu.sliding.screen.home.gallery.widgets

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.laaptu.sliding.R
import com.laaptu.sliding.model.Offer

class OfferAdapter(private val offers: List<Offer>) : RecyclerView.Adapter<OfferVH>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): OfferVH {
        return OfferVH(LayoutInflater.from(parent?.context).inflate(R.layout.offer_item, parent, false))
    }

    override fun onBindViewHolder(holder: OfferVH?, position: Int) {
        holder?.setOffer(offers[position])
    }

    override fun getItemCount(): Int {
        return offers.size
    }


}