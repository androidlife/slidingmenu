package com.laaptu.sliding

import android.app.Application
import android.content.Context
import timber.log.Timber

class MainApplication : Application() {
    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}