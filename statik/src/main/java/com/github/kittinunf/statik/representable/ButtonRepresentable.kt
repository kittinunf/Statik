package com.github.kittinunf.statik.representable

import com.github.kittinunf.statik.adapter.TypeFactory
import com.github.kittinunf.statik.model.ButtonRow
import com.github.kittinunf.statik.model.ButtonSupplementary

//Supplementary
class ButtonSupplementaryRepresentable : BaseRepresentable<ButtonSupplementary, String>(ButtonSupplementary()) {

    var onButtonSetupListener: OnTextViewSetupListener? = null

    var text: String
        set(value) {
            _value = value
        }
        get() = item.value

    override fun type(typeFactory: TypeFactory): Int = typeFactory.type(this)
}

//Row
class ButtonRowRepresentable : BaseRepresentable<ButtonRow, String>(ButtonRow()) {

    var onButtonSetupListener: OnTextViewSetupListener? = null

    var text: String
        set(value) {
            _value = value
        }
        get() = item.value

    override fun type(typeFactory: TypeFactory): Int = typeFactory.type(this)
}