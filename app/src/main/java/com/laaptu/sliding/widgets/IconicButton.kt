package com.laaptu.sliding.widgets

import android.content.Context
import android.support.v7.widget.AppCompatButton
import android.util.AttributeSet
import android.widget.Button
import com.laaptu.sliding.Injection

class IconicButton : AppCompatButton {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr) {
        typeface = Injection.fontProvider.fontAwesome
    }
}
