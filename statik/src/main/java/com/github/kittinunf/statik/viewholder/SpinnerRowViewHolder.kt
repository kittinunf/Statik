package com.github.kittinunf.statik.viewholder

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.github.kittinunf.statik.R
import com.github.kittinunf.statik.representable.SpinnerRowRepresentable

class SpinnerRowViewHolder(view: View) : StatikViewHolder(view), BindableViewHolder<SpinnerRowRepresentable> {

    override fun bind(item: SpinnerRowRepresentable) {
        item.onViewSetupListener?.invoke(itemView)

        val hintEnabled = (item.hint != null)
        //spinner
        val arrayAdapter =
                HintEnabledSpinnerAdapter(itemView.context,
                        item.spinnerItemRes ?: android.R.layout.simple_spinner_item,
                        item.list,
                        item.hint).apply {
                    setDropDownViewResource(item.dropdownViewRes
                            ?: android.R.layout.simple_spinner_dropdown_item)
                }

        val spinner = itemView.findViewById<Spinner>(R.id.statik_row_spinner).apply {
            adapter = arrayAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {}

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val offsetPosition = if (hintEnabled) {
                        if (position == 0) return
                        position - 1
                    } else {
                        position
                    }
                    item.selected = offsetPosition
                    item.onItemSelectedListener?.invoke(offsetPosition)
                }
            }
            setSelection(if (hintEnabled) item.selected + 1 else item.selected)
        }

        item.onSpinnerSetupListener?.invoke(spinner, itemView.findViewById(R.id.statik_underline))
    }

    private class HintEnabledSpinnerAdapter(context: Context, @LayoutRes spinnerItemRes: Int, data: List<String>, val textHint: String? = null)
        : ArrayAdapter<String>(context, spinnerItemRes, if (textHint != null) data.toMutableList().apply { add(0, textHint) } else data) {

        val isHintEnabled = (textHint != null)

        override fun isEnabled(position: Int): Boolean {
            return if (isHintEnabled) position != 0 else true
        }

        override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = super.getDropDownView(position, convertView, parent)
            setHintForTextView(position, view)
            return view
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = super.getView(position, convertView, parent)
            setHintForTextView(position, view)
            return view
        }

        fun setHintForTextView(position: Int, view: View) {
            if (position == 0 && isHintEnabled) {
                (view as? TextView)?.apply {
                    text = ""
                    hint = textHint
                }
            }
        }
    }
}
