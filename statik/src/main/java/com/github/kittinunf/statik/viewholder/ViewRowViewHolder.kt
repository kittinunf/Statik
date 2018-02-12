package com.github.kittinunf.statik.viewholder

import android.view.View
import android.view.ViewGroup
import com.github.kittinunf.statik.extension.inflate
import com.github.kittinunf.statik.representable.ViewRowItemRepresentable

class ViewRowViewHolder(view: View): StatikViewHolder(view), BindableViewHolder<ViewRowItemRepresentable> {

    override fun bind(item: ViewRowItemRepresentable) {
        (itemView as ViewGroup).inflate(item.layoutRes)
        item.onSetupListener?.invoke(itemView)

        //click
        item.onClickListener?.let { listener ->
            itemView.setOnClickListener { view ->
                listener.invoke(view, adapterPosition, item)
            }
        }
    }

    override fun onViewRecycled() {
        (itemView as ViewGroup).removeAllViews()
    }
}