package com.github.kittinunf.statik.model

import android.support.annotation.DrawableRes

typealias ClickHandler = (Row) -> Unit

data class Row(val primaryText: String? = null,
               val primaryTextAttribute: TextAttribute? = null,
               val secondaryText: String? = null,
               val secondaryTextAttribute: TextAttribute? = null,
               @DrawableRes val iconRes: Int? = null,
               val accessory: Accessory? = null,
               val clickHandler: ClickHandler? = null
)