package com.github.kittinunf.statik.model

import android.support.annotation.DrawableRes
import android.support.annotation.LayoutRes

typealias ClickHandler = (Row) -> Unit

data class Row(val type: Row.Type?,
               @DrawableRes val iconRes: Int? = null,
               val accessory: Accessory? = null,
               val clickHandler: ClickHandler? = null
) {
    sealed class Type {

        class Text(val primaryText: String,
                   val primaryTextAttribute: TextAttribute? = null,
                   val secondaryText: String? = null,
                   val secondaryTextAttribute: TextAttribute? = null) : Type()

        class InputText(val hint: String, val inputType: Int) : Type()

        class Custom(@LayoutRes val layoutRes: Int, val viewConfiguration: ViewConfiguration? = null) : Type()
    }
}