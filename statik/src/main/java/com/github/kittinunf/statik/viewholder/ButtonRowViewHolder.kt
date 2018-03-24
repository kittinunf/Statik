package com.github.kittinunf.statik.viewholder

import android.view.View
import android.widget.TextView
import com.github.kittinunf.statik.representable.ButtonRowRepresentable

class ButtonRowViewHolder(view: View) : StatikViewHolder(view), BindableViewHolder<ButtonRowRepresentable> {

    override fun bind(item: ButtonRowRepresentable) {
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