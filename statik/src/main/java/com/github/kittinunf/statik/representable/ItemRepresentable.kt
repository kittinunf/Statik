package com.github.kittinunf.statik.representable

import android.view.View
import com.github.kittinunf.statik.adapter.TypeFactory
import com.github.kittinunf.statik.model.Row
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

abstract class BaseRepresentable<T : Row<U>, U>(protected val item: T) : ItemRepresentable,
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