package com.github.kittinunf.statik.view

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet

class StatikView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    init {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}