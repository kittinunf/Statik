package com.github.kittinunf.statik.representable

import android.view.View
import com.github.kittinunf.statik.adapter.TypeFactory
import com.github.kittinunf.statik.model.TextRow
import com.github.kittinunf.statik.model.TextSupplementary
import com.github.kittinunf.statik.model.TwoTextRow
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

data class TextSupplementaryItemRepresentable(private val item: TextSupplementary = TextSupplementary()) : ItemRepresentable,
        ViewSetupListener, ViewClickListener<TextSupplementaryItemRepresentable>, ValueChangeListener<TextSupplementary> {

    override var onSetupListener: OnSetupListener? = null

    override var onClickListener: OnClickListener<TextSupplementaryItemRepresentable>? = null

    override var onChangedListener: OnValueChangedListener<TextSupplementary>? = null

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

data class TextRowItemRepresentable(private val row: TextRow = TextRow()) : ItemRepresentable,
        ViewSetupListener, ViewClickListener<TextRowItemRepresentable>, ValueChangeListener<TextRow> {

    override var onSetupListener: OnSetupListener? = null

    override var onClickListener: OnClickListener<TextRowItemRepresentable>? = null

    override var onChangedListener: OnValueChangedListener<TextRow>? = null

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

data class TwoTextRowItemRepresentable(private val row: TwoTextRow = TwoTextRow()) : ItemRepresentable,
        ViewSetupListener, ViewClickListener<TwoTextRowItemRepresentable>, ValueChangeListener<TwoTextRow> {

    override var onSetupListener: OnSetupListener? = null

    override var onClickListener: OnClickListener<TwoTextRowItemRepresentable>? = null

    override var onChangedListener: OnValueChangedListener<TwoTextRow>? = null

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