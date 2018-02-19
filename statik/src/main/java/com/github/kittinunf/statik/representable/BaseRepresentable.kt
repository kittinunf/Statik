package com.github.kittinunf.statik.representable

import com.github.kittinunf.statik.model.Row
import com.github.kittinunf.statik.model.Section
import com.github.kittinunf.statik.util.IdGenerator
import kotlin.properties.Delegates

typealias BaseItemRepresentable = BaseRepresentable<*, *>

abstract class BaseRepresentable<T : Row<U>, U>(protected val item: T) : ItemRepresentable,
        ViewSetupListener, ViewClickListener, ValueChangeListener<T> {

    lateinit var section: Section

    override var stableId: Long = item.stableId ?: IdGenerator.generate()

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