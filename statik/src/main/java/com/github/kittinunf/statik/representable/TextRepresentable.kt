package com.github.kittinunf.statik.representable

import com.github.kittinunf.statik.adapter.TypeFactory
import com.github.kittinunf.statik.model.TextRow
import com.github.kittinunf.statik.model.TextSupplementary
import com.github.kittinunf.statik.model.TwoTextRow

abstract class TextSupplementaryRepresentable :
        BaseRepresentable<TextSupplementary, String>(TextSupplementary()) {

    var onTextSetupListener: OnTextViewSetupListener? = null

    var text: String
        set(value) {
            _value = value
        }
        get() = _value

    var layoutRes: Int? = null
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
class TextRowRepresentable : BaseRepresentable<TextRow, String>(TextRow()) {

    var onTextSetupListener: OnTextViewSetupListener? = null

    var text: String
        set(value) {
            _value = value
        }
        get() = _value

    var iconRes: Int? = null

    override fun type(typeFactory: TypeFactory): Int = typeFactory.type(this)
}

class TwoTextRowRepresentable : BaseRepresentable<TwoTextRow, Pair<String, String?>>(TwoTextRow()) {

    var onTitleTextSetupListener: OnTextViewSetupListener? = null

    var onSummaryTextSetupListener: OnTextViewSetupListener? = null

    var titleText: String
        set(value) {
            _value = value to summaryText
        }
        get() = _value.first

    var summaryText: String?
        set(value) {
            _value = titleText to value
        }
        get() = _value.second

    var iconRes: Int? = null

    override fun type(typeFactory: TypeFactory): Int = typeFactory.type(this)
}