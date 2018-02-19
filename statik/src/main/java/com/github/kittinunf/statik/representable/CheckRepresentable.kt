package com.github.kittinunf.statik.representable

import android.widget.CompoundButton
import android.widget.TextView
import com.github.kittinunf.statik.adapter.TypeFactory
import com.github.kittinunf.statik.model.CheckRow

class CheckRowRepresentable : BaseRepresentable<CheckRow, Boolean>(CheckRow()) {

    var onTitleTextSetupListener: ((TextView) -> Unit)? = null

    var onSummaryTextSetupListener: ((TextView) -> Unit)? = null

    var onCheckBoxCheckedChangeListener: ((CompoundButton, Boolean) -> Unit)? = null

    var titleText: String = ""

    var summaryText: String? = null

    var iconRes: Int? = null

    var checked: Boolean
        set(value) {
            _value = value
        }
        get() = _value

    override fun type(typeFactory: TypeFactory): Int = typeFactory.type(this)
}