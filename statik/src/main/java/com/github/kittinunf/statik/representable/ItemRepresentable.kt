package com.github.kittinunf.statik.representable

import android.view.View
import android.widget.TextView
import com.github.kittinunf.statik.adapter.TypeFactory
import com.github.kittinunf.statik.model.Row
import com.github.kittinunf.statik.model.TextRow
import com.github.kittinunf.statik.model.TextSupplementary
import com.github.kittinunf.statik.model.TwoTextRow
import com.github.kittinunf.statik.model.ViewRow
import com.github.kittinunf.statik.model.ViewSupplementary
import kotlin.properties.Delegates

interface ItemRepresentable {
    fun type(typeFactory: TypeFactory): Int
}

typealias OnValueChangedListener<T> = (T) -> Unit

typealias OnViewSetupListener = (View) -> Unit

typealias OnClickListener = (View, Int) -> Unit

interface ViewSetupListener {
    var onViewSetupListener: OnViewSetupListener?
}

interface ViewClickListener {
    var onClickListener: OnClickListener?
}

interface ValueChangeListener<T> {
    var onChangedListener: OnValueChangedListener<T>?
}

abstract class BaseItemRepresentable<T : Row<U>, U>(protected val item: T) : ItemRepresentable,

        ViewSetupListener, ViewClickListener, ValueChangeListener<T> {

    override var onViewSetupListener: OnViewSetupListener? = null

    override var onClickListener: OnClickListener? = null

    override var onChangedListener: OnValueChangedListener<T>? = null

    protected var _value by Delegates.observable(item.value) { _, old, new ->
        if (old != new) {
            item.value = new
            onChangedListener?.invoke(item)
        }
    }
}

abstract class TextSupplementaryItemRepresentable :
        BaseItemRepresentable<TextSupplementary, Pair<String, Int?>>(TextSupplementary()) {

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

class HeaderTextSupplementaryRepresentable : TextSupplementaryItemRepresentable() {

    override fun type(typeFactory: TypeFactory): Int = typeFactory.type(this)
}

class FooterTextSupplementaryRepresentable : TextSupplementaryItemRepresentable() {

    override fun type(typeFactory: TypeFactory): Int = typeFactory.type(this)
}

class ViewSupplementaryItemRepresentable : BaseItemRepresentable<ViewSupplementary, Int>(ViewSupplementary()) {

    var layoutRes: Int
        set(value) {
            _value = value
        }
        get() = item.value

    override fun type(typeFactory: TypeFactory): Int = typeFactory.type(this)
}

class TextRowItemRepresentable : BaseItemRepresentable<TextRow, Pair<String, Int?>>(TextRow()) {

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

class TwoTextRowItemRepresentable : BaseItemRepresentable<TwoTextRow, Triple<String, String, Int?>>(TwoTextRow()) {

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

class ViewRowItemRepresentable : BaseItemRepresentable<ViewRow, Int>(ViewRow()) {

    var layoutRes: Int
        set(value) {
            _value = value
        }
        get() = item.value

    override fun type(typeFactory: TypeFactory): Int = typeFactory.type(this)
}