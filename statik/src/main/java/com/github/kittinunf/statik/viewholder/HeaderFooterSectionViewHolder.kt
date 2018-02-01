package com.github.kittinunf.statik.viewholder

import android.support.annotation.LayoutRes
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.github.kittinunf.statik.extension.inflate
import com.github.kittinunf.statik.extension.setTextAttribute
import com.github.kittinunf.statik.model.Accessory

open class HeaderFooterSectionViewHolder(view: View) : StatikViewHolder(view) {

    protected fun bindWithAccessory(@LayoutRes layoutRes: Int, item: Accessory) {
        when (item) {
            is Accessory.Title -> {
                (itemView as ViewGroup).inflate(layoutRes) { view ->
                    view.findViewById<TextView>(android.R.id.title).apply {
                        text = item.text
                        item.textAttribute?.let { attribute -> setTextAttribute(attribute) }
                    }

                    item.viewConfiguration(view)
                }
            }
            is Accessory.Custom -> {
                (itemView as ViewGroup).inflate(item.layoutRes, item.viewConfiguration)
            }
        }
    }
}