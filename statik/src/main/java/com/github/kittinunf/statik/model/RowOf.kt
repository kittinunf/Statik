package com.github.kittinunf.statik.model

import android.support.annotation.DrawableRes

sealed class RowOf<T>(var value: T? = null)

class TextRow(text: String = "", @DrawableRes iconRes: Int? = null) :
        RowOf<Pair<String, Int?>>(text to iconRes)

class TwoTextRow(titleText: String = "",
                 summaryText: String = "",
                 @DrawableRes iconRes: Int? = null) :
        RowOf<Triple<String, String, Int?>>(Triple(titleText, summaryText, iconRes))

class CheckRow(value: Boolean) : RowOf<Boolean>(value)

class ButtonRow(enabled: Boolean) : RowOf<Boolean>(enabled)
