package com.github.kittinunf.statik.representable

import android.widget.CompoundButton
import com.github.kittinunf.statik.adapter.TypeFactory
import com.github.kittinunf.statik.model.SwitchRow

class SwitchRowRepresentable : BaseRepresentable<SwitchRow, Boolean>(SwitchRow()) {

    var onTitleTextSetupListener: OnTextViewSetupListener? = null

    var onSummaryTextSetupListener: OnTextViewSetupListener? = null

    var onSwitchCheckedChangeListener: ((CompoundButton, Boolean) -> Unit)? = null

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