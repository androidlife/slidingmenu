package com.laaptu.sliding.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import com.laaptu.sliding.Injection

class IconicTextView : TextView {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr) {
        typeface = Injection.fontProvider.fontAwesome
    }

}