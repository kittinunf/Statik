package com.github.kittinunf.statik.viewholder

import android.view.View
import android.view.ViewGroup
import com.github.kittinunf.statik.extension.inflate
import com.github.kittinunf.statik.representable.ViewRowRepresentable

class ViewRowViewHolder(view: View): StatikViewHolder(view), BindableViewHolder<ViewRowRepresentable> {

    override fun bind(item: ViewRowRepresentable) {
        val frame = itemView as ViewGroup
        if (frame.childCount == 0) {
            frame.inflate(item.layoutRes)
        }

        item.onViewSetupListener?.invoke(itemView)

        //click
        item.onClickListener?.let { listener ->
            itemView.setOnClickListener { view ->
                listener.invoke(view, adapterPosition)
            }
        }
    }

    override fun onViewRecycled() {
        (itemView as ViewGroup).removeAllViews()
    }
}