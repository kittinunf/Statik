package com.github.kittinunf.statik.representable

import android.view.View
import android.widget.TextView
import com.github.kittinunf.statik.model.Row
import com.github.kittinunf.statik.model.Section
import com.github.kittinunf.statik.util.IdGenerator
import kotlin.properties.Delegates

typealias OnValueChangedListener<T> = (T) -> Unit

typealias OnViewSetupListener = (View) -> Unit

typealias OnClickListener = (View) -> Unit

typealias OnTextViewSetupListener = (TextView) -> Unit

interface ViewSetupListener {
    var onViewSetupListener: OnViewSetupListener?
}

interface ViewClickListener {
    var onClickListener: OnClickListener?
}

interface ValueChangeListener<T> {
    var onValueChangedListener: OnValueChangedListener<T>?
}

typealias AnyRepresentable = BaseRepresentable<*, *>

abstract class BaseRepresentable<T : Row<U>, U>(protected val item: T) : ItemRepresentable, ValueChangeListener<T>,
        ViewSetupListener, ViewClickListener {

    lateinit var section: Section

    override var position: Int = -1

    override var stableId: Long = item.stableId ?: IdGenerator.generate()

    open val isValid: Boolean = true

    override var onViewSetupListener: OnViewSetupListener? = null

    override var onClickListener: OnClickListener? = null

    override var onValueChangedListener: OnValueChangedListener<T>? = null

    protected var _value by Delegates.observable(item.value) { _, old, new ->
        if (old != new) {
            item.value = new
            onValueChangedListener?.invoke(item)

            if (::section.isInitialized)
                section.onValuesInSectionChangedListener?.invoke(this)
        }
    }
}
