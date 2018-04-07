package com.laaptu.sliding

import android.content.Context
import com.laaptu.sliding.utils.FontProvider

object Injection {
    fun getContext(): Context {
        return MainApplication.context
    }

    val fontProvider: FontProvider = FontProvider(getContext().assets)

}
