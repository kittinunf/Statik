package com.github.kittinunf.statik.viewholder

import android.view.View
import android.widget.TextView
import com.github.kittinunf.statik.representable.ButtonSupplementaryRepresentable

class ButtonSupplementaryViewHolder(view: View) : StatikViewHolder(view), BindableViewHolder<ButtonSupplementaryRepresentable> {

    override fun bind(item: ButtonSupplementaryRepresentable) {
        item.onViewSetupListener?.invoke(itemView)

        item.onButtonSetupListener?.invoke(itemView as TextView)

        //click
        item.onClickListener?.let { listener ->
            itemView.setOnClickListener { view ->
                listener.invoke(view, adapterPosition)
            }
        }
    }
}