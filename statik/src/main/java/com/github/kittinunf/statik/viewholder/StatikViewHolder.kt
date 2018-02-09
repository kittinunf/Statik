package com.github.kittinunf.statik.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class StatikViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    open fun onViewRecycled() {}
}

interface BindableViewHolder<in T> {
    fun bind(item: T)
}