package com.github.kittinunf.statik.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.github.kittinunf.statik.R
import com.github.kittinunf.statik.representable.TwoTextRowItemRepresentable

class TwoTextRowViewHolder(view: View) : StatikViewHolder(view), BindableViewHolder<TwoTextRowItemRepresentable> {

    override fun bind(item: TwoTextRowItemRepresentable) {
        item.onSetupListener?.invoke(itemView)

        //primary
        val titleTextView = itemView.findViewById<TextView>(R.id.statik_row_text_primary)
        titleTextView.apply {
            text = item.titleText
        }

        val summaryTextView = itemView.findViewById<TextView>(R.id.statik_row_text_secondary)
        summaryTextView.apply {
            text = item.summaryText
        }

        //image
        val iconImageView = itemView.findViewById<ImageView>(R.id.statik_row_icon)
        val iconRes = item.iconRes
        if (iconRes == null) {
            iconImageView.visibility = View.GONE
        } else {
            iconImageView.apply {
                visibility = View.VISIBLE
                setImageResource(iconRes)
            }
        }

        //click
        item.onClickListener?.let { listener ->
            itemView.setOnClickListener { view ->
                listener.invoke(view, adapterPosition, item)
            }
        }
    }
}