package com.github.kittinunf.statik.extension

import android.graphics.Color
import android.util.TypedValue
import android.widget.TextView
import com.github.kittinunf.statik.model.TextAttribute

internal fun TextView.setTextAttribute(attribute: TextAttribute) {
    attribute.textColor?.let {
        setTextColor(Color.parseColor(it))
    }

    attribute.textSizeSP?.let {
        setTextSize(TypedValue.COMPLEX_UNIT_PX, attribute.textSizeSP)
    }

    typeface = attribute.typeface

    gravity = attribute.textGravity
}