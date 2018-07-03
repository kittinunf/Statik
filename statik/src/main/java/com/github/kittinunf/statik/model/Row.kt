package com.github.kittinunf.statik.model

import android.support.annotation.LayoutRes

sealed class Row<T>(var value: T,
                    var stableId: Long? = null)

// Supplementary
class TextSupplementary(text: String = "",
                        stableId: Long? = null) : Row<String>(text, stableId)

class ViewSupplementary(@LayoutRes layoutRes: Int = 0,
                        stableId: Long? = null) : Row<Int>(layoutRes, stableId)

class ButtonSupplementary(text: String = "",
                          stableId: Long? = null) : Row<String>(text, stableId)

// Row
class TextRow(text: String = "",
              stableId: Long? = null) : Row<String>(text, stableId)

class TwoTextRow(titleText: String = "",
                 summaryText: String? = null,
                 stableId: Long? = null) : Row<Pair<String, String?>>(titleText to summaryText, stableId)

class ViewRow(@LayoutRes layoutRes: Int = 0,
              stableId: Long? = null) : Row<Int>(layoutRes, stableId)

class CheckRow(checked: Boolean = false,
               stableId: Long? = null) : Row<Boolean>(checked, stableId)

                class SwitchRow(checked: Boolean = false,
                stableId: Long? = null) : Row<Boolean>(checked, stableId)

class InputRow(input: String = "",
               stableId: Long? = null) : Row<String>(input, stableId)

class SpinnerRow(selected: Int = -1,
                 list: List<String> = emptyList(),
                 stableId: Long? = null) : Row<Pair<Int, List<String>>>(selected to list, stableId)

class DateRow(year: Int = -1,
              month: Int = -1,
              dayOfMonth: Int = -1,
              stableId: Long? = null) : Row<Triple<Int, Int, Int>>(Triple(year, month, dayOfMonth), stableId)

class ButtonRow(text: String = "",
                stableId: Long? = null) : Row<String>(text, stableId)