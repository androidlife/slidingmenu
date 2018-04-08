package com.laaptu.sliding.widgets

import android.content.Context
import android.support.v7.widget.AppCompatButton
import android.util.AttributeSet
import com.laaptu.sliding.Injection

class IconicButton : AppCompatButton {
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        typeface = Injection.fontProvider.fontAwesome
    }
}
