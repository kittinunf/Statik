package com.github.kittinunf.statik.representable

import android.support.annotation.LayoutRes
import android.view.View
import android.widget.Spinner
import com.github.kittinunf.statik.adapter.TypeFactory
import com.github.kittinunf.statik.model.SpinnerRow

class SpinnerRowRepresentable : BaseRepresentable<SpinnerRow, Pair<Int, List<String>>>(SpinnerRow()) {

    var onItemSelectedListener: ((Int) -> Unit)? = null

    var onSpinnerSetupListener: ((Spinner, View) -> Unit)? = null

    @LayoutRes
    var dropdownViewRes: Int? = null

    @LayoutRes
    var spinnerItemRes: Int? = null

    var hint: String? = null

    var selected: Int
        set(value) {
            _value = value to _value.second
        }
        get() = _value.first

    var list: List<String>
        set(value) {
            _value = _value.first to value
        }
        get() = _value.second

    override fun type(typeFactory: TypeFactory): Int = typeFactory.type(this)
}