package com.github.kittinunf.statik.model

import android.support.annotation.DrawableRes
import android.support.annotation.LayoutRes

sealed class RowOf<T>(var value: T, val section: Section? = null)

// Supplementary
class TextSupplementary(text: String = "", @LayoutRes widgetRes: Int? = null) :
        RowOf<Pair<String, Int?>>(text to widgetRes)

class ViewSupplementary(@LayoutRes layoutRes: Int = 0) : RowOf<Int>(layoutRes)

// Row
class TextRow(text: String = "", @DrawableRes iconRes: Int? = null) :
        RowOf<Pair<String, Int?>>(text to iconRes)

class TwoTextRow(titleText: String = "",
                 summaryText: String = "",
                 @DrawableRes iconRes: Int? = null) :
        RowOf<Triple<String, String, Int?>>(Triple(titleText, summaryText, iconRes))

class CheckRow(value: Boolean) : RowOf<Boolean>(value)

class ButtonRow(enabled: Boolean) : RowOf<Boolean>(enabled)
