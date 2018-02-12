package com.github.kittinunf.statik.model

import android.support.annotation.DrawableRes
import android.support.annotation.LayoutRes

sealed class Row<T>(var value: T)

// Supplementary
class TextSupplementary(text: String = "", @LayoutRes widgetRes: Int? = null) :
        Row<Pair<String, Int?>>(text to widgetRes)

class ViewSupplementary(@LayoutRes layoutRes: Int = 0) : Row<Int>(layoutRes)

// Row
class TextRow(text: String = "", @DrawableRes iconRes: Int? = null) :
        Row<Pair<String, Int?>>(text to iconRes)

class TwoTextRow(titleText: String = "",
                 summaryText: String = "",
                 @DrawableRes iconRes: Int? = null) :
        Row<Triple<String, String, Int?>>(Triple(titleText, summaryText, iconRes))

class ViewRow(@LayoutRes layoutRes: Int = 0) : Row<Int>(layoutRes)

class CheckRow(value: Boolean) : Row<Boolean>(value)


