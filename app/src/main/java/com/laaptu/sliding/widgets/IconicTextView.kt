package com.laaptu.sliding.widgets

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import com.laaptu.sliding.Injection

class IconicTextView : AppCompatTextView {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr) {
        typeface = Injection.fontProvider.fontAwesome
    }

}