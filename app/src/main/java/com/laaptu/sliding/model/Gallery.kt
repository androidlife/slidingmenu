package com.laaptu.sliding.model

open class Deal(val id: Int, val imgResId: Int)
class Offer(id: Int, imgResId: Int, val title: String) : Deal(id, imgResId)
