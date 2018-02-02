package com.github.kittinunf.statik.model

import android.support.annotation.LayoutRes
import android.view.View

typealias ViewConfiguration = (View) -> Unit

sealed class Accessory(val viewConfiguration: ViewConfiguration?) {
    class Title(val text: String,
                val textAttribute: TextAttribute? = null,
                viewConfiguration: ((View) -> Unit)? = null) : Accessory(viewConfiguration)

    class Custom(@LayoutRes val layoutRes: Int,
                 viewConfiguration: ((View) -> Unit)? = null) : Accessory(viewConfiguration)
}