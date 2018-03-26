package com.github.kittinunf.statik.representable

import android.widget.TextView
import com.github.kittinunf.statik.adapter.TypeFactory
import com.github.kittinunf.statik.model.DateRow
import java.text.SimpleDateFormat
import java.util.*

typealias Year = Int
typealias Month = Int
typealias DayOfMonth = Int
typealias OnDateSelectedListener = (TextView, Int, Int, Int) -> Unit

class DateRowRepresentable : BaseRepresentable<DateRow, Triple<Year, Month, DayOfMonth>>(DateRow()) {

    var text: String = ""

    var hint: String = ""

    var dateFormatter: SimpleDateFormat? = null

    var dialogStartingDate: Calendar? = null

    var onTextSetupListener: OnTextViewSetupListener? = null

    var onResultTextSetupListener: OnTextViewSetupListener? = null

    var onDateSelectedListener: OnDateSelectedListener? = null

    var year: Year
        set(value) {
            _value = Triple(value, _value.second, _value.third)
        }
        get() = _value.first

    var month: Month
        set(value) {
            _value = Triple(_value.first, value, _value.third)
        }
        get() = _value.second

    var dayOfMonth: DayOfMonth
        set(value) {
            _value = Triple(_value.first, _value.second, value)
        }
        get() = _value.third

    override fun type(typeFactory: TypeFactory): Int = typeFactory.type(this)
}