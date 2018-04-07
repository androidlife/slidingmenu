package com.laaptu.sliding

import android.app.Application
import android.content.Context

class MainApplication : Application() {
    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}