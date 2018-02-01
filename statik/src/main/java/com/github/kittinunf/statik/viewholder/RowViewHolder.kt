package com.github.kittinunf.statik.viewholder

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.github.kittinunf.statik.R
import com.github.kittinunf.statik.extension.inflate
import com.github.kittinunf.statik.extension.setTextAttribute
import com.github.kittinunf.statik.model.Accessory
import com.github.kittinunf.statik.model.TextAttribute
import com.github.kittinunf.statik.representable.RowItemRepresentable

class RowViewHolder(view: View) : StatikViewHolder(view), BindableViewHolder<RowItemRepresentable> {

    override fun bind(item: RowItemRepresentable) {
        // default two lines
        val row = item.row
        (itemView as ViewGroup).inflate(R.layout.statik_row_two_lines) { view ->
            val iconImageView = view.findViewById<ImageView>(R.id.statik_row_icon)
            if (row.iconRes == null) {
                iconImageView.visibility = View.GONE
            } else {
                iconImageView.setImageResource(row.iconRes)
            }

            setTextAttribute(view.findViewById(R.id.statik_row_text_primary),
                    row.primaryText,
                    row.primaryTextAttribute)

            setTextAttribute(view.findViewById(R.id.statik_row_text_secondary),
                    row.secondaryText,
                    row.secondaryTextAttribute)

            view.setOnClickListener { row.clickHandler?.invoke(row) }

            row.accessory?.let { accessory ->
                val widgetFrame = view.findViewById<LinearLayout>(R.id.statik_row_widget_frame)
                when (accessory) {
                    is Accessory.Title -> {
                        widgetFrame.inflate(R.layout.statik_text_accessory) {
                            setTextAttribute(it.findViewById(android.R.id.title), accessory.text, accessory.textAttribute)
                            accessory.viewConfiguration(it)
                        }
                    }
                    is Accessory.Custom -> {
                        widgetFrame.inflate(accessory.layoutRes, accessory.viewConfiguration)
                    }
                }
            }
        }
    }

    private fun setTextAttribute(textView: TextView, text: String?, textAttribute: TextAttribute?) {
        if (text == null) {
            textView.visibility = View.GONE
        } else {
            textView.text = text
            textAttribute?.let { attribute -> textView.setTextAttribute(attribute) }
        }
    }
}