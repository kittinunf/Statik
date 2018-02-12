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

abstract class BaseItemRepresentable : ItemRepresentable,
        ViewSetupListener, ViewClickListener, ValueChangeListener<Row<*>> {

    override var onViewSetupListener: OnViewSetupListener? = null

    override var onClickListener: OnClickListener? = null

    override var onChangedListener: OnValueChangedListener<Row<*>>? = null

    protected abstract val item: Row<*>
}

data class TextSupplementaryItemRepresentable(override val item: TextSupplementary = TextSupplementary()) :
        BaseItemRepresentable() {

    var onTextSetupListener: ((TextView) -> Unit)? = null

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

data class ViewSupplementaryItemRepresentable(override val item: ViewSupplementary = ViewSupplementary()) :
        BaseItemRepresentable() {

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

data class TextRowItemRepresentable(override val item: TextRow = TextRow()) :
        BaseItemRepresentable() {

    var onTextSetupListener: ((TextView) -> Unit)? = null

    private var _value by Delegates.observable(item.value) { _, old, new ->
        if (old != new) {
            item.value = new
            onChangedListener?.invoke(item)
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

data class TwoTextRowItemRepresentable(override val item: TwoTextRow = TwoTextRow()) :
        BaseItemRepresentable() {

    var onTitleTextSetupListener: ((TextView) -> Unit)? = null

    var onSummaryTextSetupListener: ((TextView) -> Unit)? = null

    private var _value by Delegates.observable(item.value) { _, old, new ->
        if (old != new) {
            item.value = new
            onChangedListener?.invoke(item)
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

data class ViewRowItemRepresentable(override val item: ViewRow = ViewRow()) :
        BaseItemRepresentable() {

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