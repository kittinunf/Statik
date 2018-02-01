package com.github.kittinunf.statik.extension

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

internal fun ViewGroup.inflate(@LayoutRes res: Int, configuration: (View) -> Unit = {}): View {
    val view = LayoutInflater.from(context).inflate(res, this, true)
    configuration(view)
    return view
}