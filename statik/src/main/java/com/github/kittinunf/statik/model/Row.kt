package com.github.kittinunf.statik.model

import android.support.annotation.LayoutRes

sealed class Row<T>(var value: T,
                    var stableId: Long? = null)

// Supplementary
class TextSupplementary(text: String = "",
                        stableId: Long? = null) : Row<String>(text, stableId)

class ViewSupplementary(@LayoutRes layoutRes: Int = 0,
                        stableId: Long? = null) : Row<Int>(layoutRes, stableId)

// Row
class TextRow(text: String = "",
              stableId: Long? = null) : Row<String>(text, stableId)

class TwoTextRow(titleText: String = "",
                 summaryText: String = "",
                 stableId: Long? = null) : Row<Pair<String, String>>(titleText to summaryText, stableId)

class ViewRow(@LayoutRes layoutRes: Int = 0,
              stableId: Long? = null) : Row<Int>(layoutRes, stableId)

class CheckRow(value: Boolean = false,
               stableId: Long? = null) : Row<Boolean>(value, stableId)


