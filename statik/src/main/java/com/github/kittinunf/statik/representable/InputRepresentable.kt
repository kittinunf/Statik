package com.github.kittinunf.statik.representable

import android.support.design.widget.TextInputLayout
import android.text.InputType
import com.github.kittinunf.statik.adapter.TypeFactory
import com.github.kittinunf.statik.model.InputRow

class InputRowRepresentable : BaseRepresentable<InputRow, String>(InputRow()) {

    var onInputLayoutSetupListener: ((TextInputLayout) -> Unit)? = null

    var onValidateInput: ((CharSequence?) -> Boolean)? = null

    var hint: String? = null

    var inputType: Int = InputType.TYPE_CLASS_TEXT

    var text: String
        set(value) {
            _value = value
        }
        get() = _value

    var error: String? = null

    override fun type(typeFactory: TypeFactory): Int = typeFactory.type(this)
}