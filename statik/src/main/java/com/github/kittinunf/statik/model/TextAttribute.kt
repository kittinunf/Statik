package com.github.kittinunf.statik.model

import android.graphics.Typeface
import android.view.Gravity

data class TextAttribute(val textColor: String?,
                         val textSizeSP: Float? = null,
                         val typeface: Typeface = Typeface.DEFAULT,
                         val textGravity: Int = Gravity.START)