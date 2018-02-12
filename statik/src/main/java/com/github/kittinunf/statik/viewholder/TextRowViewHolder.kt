package com.github.kittinunf.statik.viewholder

import android.support.v4.widget.TextViewCompat
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.github.kittinunf.statik.R
import com.github.kittinunf.statik.representable.TextRowItemRepresentable

class TextRowViewHolder(view: View) : StatikViewHolder(view), BindableViewHolder<TextRowItemRepresentable> {

    override fun bind(item: TextRowItemRepresentable) {
        item.onViewSetupListener?.invoke(itemView)

        //primary
        val textView = itemView.findViewById<TextView>(R.id.statik_row_text)
        textView.apply {
            text = item.text
        }

        val textSetup = item.onTextSetupListener
        if (textSetup == null) {
            TextViewCompat.setTextAppearance(textView, R.style.Widget_Statik_Base_PrimaryTextItem)
        } else {
            textSetup(textView)
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
                listener.invoke(view, adapterPosition)
            }
        }
    }
}