package com.github.kittinunf.statik.viewholder

import android.view.View
import android.view.ViewGroup
import com.github.kittinunf.statik.extension.inflate
import com.github.kittinunf.statik.representable.ViewSupplementaryItemRepresentable

class ViewSupplementaryViewHolder(view: View) : StatikViewHolder(view), BindableViewHolder<ViewSupplementaryItemRepresentable> {

    override fun bind(item: ViewSupplementaryItemRepresentable) {
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