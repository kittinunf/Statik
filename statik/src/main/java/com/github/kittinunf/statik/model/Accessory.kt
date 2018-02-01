package com.github.kittinunf.statik.model

import android.support.annotation.LayoutRes
import android.view.View

sealed class Accessory(val viewConfiguration: (View) -> Unit) {
    class Title(val text: String,
                val textAttribute: TextAttribute? = null,
                viewConfiguration: (View) -> Unit = {}) : Accessory(viewConfiguration)

    class Custom(@LayoutRes val layoutRes: Int,
                 viewConfiguration: (View) -> Unit = {}) : Accessory(viewConfiguration)
}