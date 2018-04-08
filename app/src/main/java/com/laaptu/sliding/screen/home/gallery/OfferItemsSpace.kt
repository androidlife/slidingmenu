package com.laaptu.sliding.screen.home.gallery

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class OfferItemsSpace(private var itemSpace: Int)
    : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        val position = parent.getChildAdapterPosition(view)
        if (position == RecyclerView.NO_POSITION)
            return
        if (position == 0) {
            return
        }
        outRect.set(itemSpace, 0, 0, 0)
    }
}