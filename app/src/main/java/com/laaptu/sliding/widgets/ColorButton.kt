package com.laaptu.sliding.widgets

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatButton
import android.util.AttributeSet
import com.laaptu.sliding.R

class ColorButton : AppCompatButton {

    private var defaultTextColor = android.R.color.black
    private var selectedTextColor = android.R.color.white

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        if (attrs == null)
            return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ColorButton)
        try {
            defaultTextColor = typedArray.getColor(R.styleable.ColorButton_text_color_default,
                    ContextCompat.getColor(context, android.R.color.black))
            selectedTextColor = typedArray.getColor(R.styleable.ColorButton_text_color_selected,
                    ContextCompat.getColor(context, android.R.color.white))
        } finally {
            typedArray.recycle()
        }
        setChecked(false)
    }

    fun setChecked(selected: Boolean) {
        when (selected) {
            true -> setTextColor(selectedTextColor)
            false -> setTextColor(defaultTextColor)
        }
    }

    fun getSelectedTextColor(): Int {
        return selectedTextColor
    }
}
