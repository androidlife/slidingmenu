package com.laaptu.sliding.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.util.DisplayMetrics
import com.laaptu.sliding.R
import com.laaptu.sliding.model.Offer
import com.laaptu.sliding.model.Story


fun isConnectedToNetwork(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnectedOrConnecting
}

fun getAllStories(): List<Story> {
    return listOf(Story(0, R.drawable.story_cloud, "Read cloud services"),
            Story(1, R.drawable.story_customer, "Wanna know customer feedback"),
            Story(2, R.drawable.story_success, "Here is our success story"),
            Story(3, R.drawable.story_transformation, "This is how we transformed"))
}

fun getAllOffers(): List<Offer> {
    return listOf(Offer(0, R.drawable.offer_fast, "Get fastest internet speed at $5/month"),
            Offer(1, R.drawable.offer_nat, "All nat geo channels for $1/week"),
            Offer(2, R.drawable.offer_nbn, "Want to enjoy home internet with incredible speed"),
            Offer(3, R.drawable.offer_business, "Want to get the best for your business"),
            Offer(4, R.drawable.offer_sim, "Experience the double data explosion for your sim"))
}

fun getScreenWidthHeight(activity: Activity): IntArray {
    val widthHeight = IntArray(2)
    val displayMetrics = DisplayMetrics()
    activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
    widthHeight[0] = displayMetrics.widthPixels
    widthHeight[1] = displayMetrics.heightPixels
    return widthHeight
}
