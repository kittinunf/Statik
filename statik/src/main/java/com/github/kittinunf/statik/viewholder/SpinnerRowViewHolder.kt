package com.github.kittinunf.statik.viewholder

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.github.kittinunf.statik.R
import com.github.kittinunf.statik.representable.SpinnerRepresentable

class SpinnerRowViewHolder(view: View) : StatikViewHolder(view), BindableViewHolder<SpinnerRepresentable> {

    override fun bind(item: SpinnerRepresentable) {
        item.onViewSetupListener?.invoke(itemView)

        //spinner
        val arrayAdapter = ArrayAdapter(itemView.context,
                item.spinnerItemRes ?: android.R.layout.simple_spinner_item,
                item.list).apply {
            setDropDownViewResource(item.dropdownViewRes ?: android.R.layout.simple_spinner_dropdown_item)
        }

        itemView.findViewById<Spinner>(R.id.statik_row_spinner).apply {
            adapter = arrayAdapter
            setSelection(item.selected)
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {}

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    item.onItemSelectedListener?.invoke(position, item.list[position])
                }
            }
        }
    }
}