package com.github.kittinunf.statik.extension

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.kittinunf.statik.model.ViewConfiguration

internal fun ViewGroup.inflate(@LayoutRes res: Int, configuration: ViewConfiguration?): View {
    val view = LayoutInflater.from(context).inflate(res, this, true)
    configuration?.invoke(view)
    return view
}