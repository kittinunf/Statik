package com.github.kittinunf.statik.representable

import android.view.View
import android.widget.TextView
import com.github.kittinunf.statik.adapter.TypeFactory
import com.github.kittinunf.statik.model.RowOf
import com.github.kittinunf.statik.model.TextRow
import com.github.kittinunf.statik.model.TextSupplementary
import com.github.kittinunf.statik.model.TwoTextRow
import com.github.kittinunf.statik.model.ViewSupplementary
import kotlin.properties.Delegates

interface ItemRepresentable {
    fun type(typeFactory: TypeFactory): Int
}

typealias OnValueChangedListener<T> = (T) -> Unit

typealias OnSetupListener = (View) -> Unit

typealias OnClickListener<T> = (View, Int, T) -> Unit

interface ViewSetupListener {
    var onSetupListener: OnSetupListener?
}

interface ViewClickListener<T> {
    var onClickListener: OnClickListener<T>?
}

interface ValueChangeListener<T> {
    var onChangedListener: OnValueChangedListener<T>?
}

abstract class BaseItemRepresentable<T : ItemRepresentable, U : RowOf<*>> : ItemRepresentable, ViewSetupListener, ViewClickListener<T>, ValueChangeListener<U> {
    override var onSetupListener: OnSetupListener? = null

    override var onClickListener: OnClickListener<T>? = null

    override var onChangedListener: OnValueChangedListener<U>? = null
}

data class TextSupplementaryItemRepresentable(private val item: TextSupplementary = TextSupplementary()) :
        BaseItemRepresentable<TextSupplementaryItemRepresentable, TextSupplementary>() {

    private var _value by Delegates.observable(item.value) { _, old, new ->
        if (old != new) {
            item.value = new
            onChangedListener?.invoke(item)
        }
    }

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

    override fun type(typeFactory: TypeFactory): Int = typeFactory.type(this)
}

data class ViewSupplementaryItemRepresentable(private val item: ViewSupplementary = ViewSupplementary()) :
        BaseItemRepresentable<ViewSupplementaryItemRepresentable, ViewSupplementary>() {

    private var _value by Delegates.observable(item.value) { _, old, new ->
        if (old != new) {
            item.value = new
            onChangedListener?.invoke(item)
        }
    }

    var layoutRes: Int
        set(value) {
            _value = value
        }
        get() = item.value


    override fun type(typeFactory: TypeFactory): Int = typeFactory.type(this)
}

data class TextRowItemRepresentable(private val row: TextRow = TextRow()) :
        BaseItemRepresentable<TextRowItemRepresentable, TextRow>() {

    var onTextSetupListener: ((TextView) -> Unit)? = null

    private var _value by Delegates.observable(row.value) { _, old, new ->
        if (old != new) {
            row.value = new
            onChangedListener?.invoke(row)
        }
    }

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

data class TwoTextRowItemRepresentable(private val row: TwoTextRow = TwoTextRow()) :
        BaseItemRepresentable<TwoTextRowItemRepresentable, TwoTextRow>() {

    var onTitleTextSetupListener: ((TextView) -> Unit)? = null

    var onSummaryTextSetupListener: ((TextView) -> Unit)? = null

    private var _value by Delegates.observable(row.value) { _, old, new ->
        if (old != new) {
            row.value = new
            onChangedListener?.invoke(row)
        }
    }

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