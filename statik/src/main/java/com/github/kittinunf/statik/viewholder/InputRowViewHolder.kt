package com.github.kittinunf.statik.viewholder

import android.support.design.widget.TextInputLayout
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import com.github.kittinunf.statik.R
import com.github.kittinunf.statik.representable.InputRowRepresentable

class InputRowViewHolder(view: View) : StatikViewHolder(view), BindableViewHolder<InputRowRepresentable> {

    override fun bind(item: InputRowRepresentable) {
        item.onViewSetupListener?.invoke(itemView)

        //input
        val inputLayout = itemView.findViewById<TextInputLayout>(R.id.statik_row_input_layout)
        inputLayout.apply {
            hint = item.hint
            editText?.apply {
                inputType = item.inputType
                setText(item.text)
                setSelection(item.text.length)
            }
        }

        item.onInputLayoutSetupListener?.invoke(inputLayout)

        val editText = itemView.findViewById<TextView>(R.id.statik_row_input_edit)
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (item.position != adapterPosition) return

                val isValid = item.onValidateInput?.invoke(s) ?: true
                inputLayout.isErrorEnabled = !isValid
                if (!isValid) {
                    inputLayout.error = item.error
                }
                item.text = s.toString()
            }
        })

        item.onClickListener?.let { listener ->
            itemView.setOnClickListener {
                listener.invoke(it, adapterPosition)
            }
        }
    }
}