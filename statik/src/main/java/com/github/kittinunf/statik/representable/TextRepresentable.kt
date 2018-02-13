package com.github.kittinunf.statik.representable

import android.widget.TextView
import com.github.kittinunf.statik.adapter.TypeFactory
import com.github.kittinunf.statik.model.TextRow
import com.github.kittinunf.statik.model.TextSupplementary
import com.github.kittinunf.statik.model.TwoTextRow

abstract class TextSupplementaryRepresentable :
        BaseRepresentable<TextSupplementary, Pair<String, Int?>>(TextSupplementary()) {

    var onTextSetupListener: ((TextView) -> Unit)? = null

    var text: String
        set(value) {
            _value = value to layoutRes
        }
        get() = _value.first

    var layoutRes: Int?
        set(value) {
            _value = text to value
        }
        get() = _value.second
}

//Header
class HeaderTextSupplementaryRepresentable : TextSupplementaryRepresentable() {

    override fun type(typeFactory: TypeFactory): Int = typeFactory.type(this)
}

//Footer
class FooterTextSupplementaryRepresentable : TextSupplementaryRepresentable() {

    override fun type(typeFactory: TypeFactory): Int = typeFactory.type(this)
}

//Row
class TextRowRepresentable : BaseRepresentable<TextRow, Pair<String, Int?>>(TextRow()) {

    var onTextSetupListener: ((TextView) -> Unit)? = null

    var text: String
        set(value) {
            _value = value to iconRes
        }
        get() = _value.first

    var iconRes: Int?
        set(value) {
            _value = text to value
        }
        get() = _value.second

    override fun type(typeFactory: TypeFactory): Int = typeFactory.type(this)
}

class TwoTextRowRepresentable : BaseRepresentable<TwoTextRow, Triple<String, String, Int?>>(TwoTextRow()) {

    var onTitleTextSetupListener: ((TextView) -> Unit)? = null

    var onSummaryTextSetupListener: ((TextView) -> Unit)? = null

    var titleText: String
        set(value) {
            _value = Triple(value, summaryText, iconRes)
        }
        get() = _value.first

    var summaryText: String
        set(value) {
            _value = Triple(titleText, value, iconRes)
        }
        get() = _value.second

    var iconRes: Int?
        set(value) {
            _value = Triple(titleText, summaryText, value)
        }
        get() = _value.third

    override fun type(typeFactory: TypeFactory): Int = typeFactory.type(this)
}