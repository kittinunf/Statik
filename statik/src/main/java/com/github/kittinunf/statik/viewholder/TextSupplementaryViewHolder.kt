package com.github.kittinunf.statik.viewholder

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.github.kittinunf.statik.R
import com.github.kittinunf.statik.representable.TextSupplementaryItemRepresentable

class TextSupplementaryViewHolder(view: View) : StatikViewHolder(view), BindableViewHolder<TextSupplementaryItemRepresentable> {

    override fun bind(item: TextSupplementaryItemRepresentable) {
        item.onSetupListener?.invoke(itemView)

        //primary
        val primaryTextView = itemView.findViewById<TextView>(R.id.statik_row_text_primary)
        primaryTextView.apply {
            text = item.text
        }

        //widget frame
        val widgetFrame = itemView.findViewById<ViewGroup>(R.id.statik_row_widget_frame)
        if (item.layoutRes == null) {
            widgetFrame.visibility = View.GONE
        } else {
            widgetFrame.visibility = View.VISIBLE
        }

        //click
        itemView.setOnClickListener {
            item.onClickListener?.invoke(it, adapterPosition, item)
        }
    }
}