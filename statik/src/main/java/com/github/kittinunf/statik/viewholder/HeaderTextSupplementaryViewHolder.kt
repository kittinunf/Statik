package com.github.kittinunf.statik.viewholder

import android.support.v4.widget.TextViewCompat
import android.view.View
import android.widget.TextView
import com.github.kittinunf.statik.R
import com.github.kittinunf.statik.representable.TextSupplementaryRepresentable

class HeaderTextSupplementaryViewHolder(view: View) : TextSupplementaryViewHolder(view) {

    override fun bind(item: TextSupplementaryRepresentable) {
        super.bind(item)

        val textView = itemView.findViewById<TextView>(R.id.statik_row_text)

        val textSetup = item.onTextSetupListener
        if (textSetup == null) {
            TextViewCompat.setTextAppearance(textView, R.style.Widget_Statik_Base_HeaderTextItem)
        } else {
            textSetup(textView)
        }
    }
}