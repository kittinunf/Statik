package com.github.kittinunf.statik.model

import android.support.annotation.DrawableRes

sealed class RowOf<T>(var value: T? = null)

class TextRow(text: String, @DrawableRes val iconRes: Int?) : RowOf<String>(text)

class TwoTextRow(primary: String, secondary: String) : RowOf<Pair<String, String>>(primary to secondary)

class CheckRow(value: Boolean) : RowOf<Boolean>(value)

class ButtonRow(enabled: Boolean) : RowOf<Boolean>(enabled)
