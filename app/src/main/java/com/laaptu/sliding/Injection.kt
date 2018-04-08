package com.laaptu.sliding

import com.laaptu.sliding.utils.FontProvider

object Injection {
    val fontProvider: FontProvider = FontProvider(MainApplication.context.assets)
}
