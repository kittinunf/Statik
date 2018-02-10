package com.github.kittinunf.statik.viewholder

import android.support.design.widget.TextInputLayout
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.github.kittinunf.statik.R
import com.github.kittinunf.statik.extension.inflate
import com.github.kittinunf.statik.model.Accessory
import com.github.kittinunf.statik.model.Row
import com.github.kittinunf.statik.representable.RowItemRepresentable

class RowViewHolder(view: View) : StatikViewHolder(view), BindableViewHolder<RowItemRepresentable> {

    override fun bind(item: RowItemRepresentable) {
        // default two lines
        val row = item.row
        when (row.type) {
            is Row.Type.Text -> {
                val type = row.type
                (itemView as ViewGroup).inflate(R.layout.statik_row_two_lines) { view ->
                    val iconImageView = view.findViewById<ImageView>(R.id.statik_row_icon)
                    if (row.iconRes == null) {
                        iconImageView.visibility = View.GONE
                    } else {
                        iconImageView.setImageResource(row.iconRes)
                    }

                    row.clickHandler?.let { handler ->
                        view.setOnClickListener { handler.invoke(row) }
                    }

                    val accessory = row.accessory
                    val widgetFrame = view.findViewById<LinearLayout>(R.id.statik_row_widget_frame)
                    if (accessory != null) {
                        when (accessory) {
                            is Accessory.Title -> {
                                widgetFrame.inflate(R.layout.statik_text_accessory) {
                                    accessory.viewConfiguration?.invoke(it)
                                }
                            }
                            is Accessory.Custom -> {
                                widgetFrame.inflate(accessory.layoutRes, accessory.viewConfiguration)
                            }
                        }
                    } else {
                        widgetFrame.visibility = View.GONE
                    }
                }
            }

            is Row.Type.InputText -> {
                (itemView as ViewGroup).inflate(R.layout.statik_text_input) { view ->
                    val input = view.findViewById<TextInputLayout>(android.R.id.input)
                    input.hint = row.type.hint
                    input.editText?.inputType = row.type.inputType
                    input.editText?.setText(row.type.text)
                }
            }

            is Row.Type.Custom -> {
                (itemView as ViewGroup).inflate(row.type.layoutRes, row.type.viewConfiguration)
            }
        }
    }
}