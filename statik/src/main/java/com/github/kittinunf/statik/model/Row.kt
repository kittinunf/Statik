package com.github.kittinunf.statik.model

import android.support.annotation.DrawableRes
import android.support.annotation.LayoutRes
import android.text.InputType

typealias ClickHandler = (Row) -> Unit

data class Row(val type: Row.Type?,
               @DrawableRes val iconRes: Int? = null,
               val accessory: Accessory? = null,
               val clickHandler: ClickHandler? = null
) {
    sealed class Type {

        class Text(val primaryText: String,
                   val secondaryText: String? = null) : Type()

        class InputText(val hint: String,
                        val text: String? = null,
                        val inputType: Int = InputType.TYPE_CLASS_TEXT) : Type()

        class Custom(@LayoutRes val layoutRes: Int, val viewConfiguration: ViewConfiguration? = null) : Type()
    }
}