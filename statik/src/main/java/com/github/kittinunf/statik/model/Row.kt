package com.github.kittinunf.statik.model

import android.support.annotation.DrawableRes
import android.support.annotation.LayoutRes

sealed class Row<T>(var value: T,
                    var stableId: Long? = null)

// Supplementary
class TextSupplementary(text: String = "",
                        @LayoutRes widgetRes: Int? = null,
                        stableId: Long? = null) : Row<Pair<String, Int?>>(text to widgetRes, stableId)

class ViewSupplementary(@LayoutRes layoutRes: Int = 0,
                        stableId: Long? = null) : Row<Int>(layoutRes, stableId)

// Row
class TextRow(text: String = "",
              @DrawableRes iconRes: Int? = null,
              stableId: Long? = null) : Row<Pair<String, Int?>>(text to iconRes, stableId)

class TwoTextRow(titleText: String = "",
                 summaryText: String = "",
                 @DrawableRes iconRes: Int? = null,
                 stableId: Long? = null) : Row<Triple<String, String, Int?>>(Triple(titleText, summaryText, iconRes), stableId)

class ViewRow(@LayoutRes layoutRes: Int = 0,
              stableId: Long? = null) : Row<Int>(layoutRes, stableId)

class CheckRow(value: Boolean = false,
               stableId: Long? = null) : Row<Boolean>(value, stableId)


