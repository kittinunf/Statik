package com.github.kittinunf.statik.representable

import android.view.View
import android.widget.TextView
import com.github.kittinunf.statik.adapter.TypeFactory

interface ItemRepresentable {
    fun type(typeFactory: TypeFactory): Int

    val stableId: Long

    var position: Int
}

typealias OnValueChangedListener<T> = (T) -> Unit

typealias OnViewSetupListener = (View) -> Unit

typealias OnClickListener = (View, Int) -> Unit

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