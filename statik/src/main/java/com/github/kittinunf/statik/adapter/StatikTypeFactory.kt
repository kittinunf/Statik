package com.github.kittinunf.statik.adapter

import android.view.View
import com.github.kittinunf.statik.R
import com.github.kittinunf.statik.representable.TextRowItemRepresentable
import com.github.kittinunf.statik.representable.TextSupplementaryItemRepresentable
import com.github.kittinunf.statik.representable.TwoTextRowItemRepresentable
import com.github.kittinunf.statik.representable.ViewSupplementaryItemRepresentable
import com.github.kittinunf.statik.viewholder.StatikViewHolder
import com.github.kittinunf.statik.viewholder.TextRowViewHolder
import com.github.kittinunf.statik.viewholder.TextSupplementaryViewHolder
import com.github.kittinunf.statik.viewholder.TwoTextRowViewHolder
import com.github.kittinunf.statik.viewholder.ViewSupplementaryViewHolder

interface TypeFactory {

    fun type(header: TextSupplementaryItemRepresentable): Int
    fun type(view: ViewSupplementaryItemRepresentable): Int

    fun type(textRow: TextRowItemRepresentable): Int
    fun type(twoTextRow: TwoTextRowItemRepresentable): Int

    fun viewHolder(type: Int, view: View): StatikViewHolder
}

internal val defaultTypeFactory = object : TypeFactory {

    override fun type(header: TextSupplementaryItemRepresentable): Int = R.layout.statik_text_supplementary

    override fun type(view: ViewSupplementaryItemRepresentable): Int = R.layout.statik_view_placeholder

    override fun type(textRow: TextRowItemRepresentable): Int = R.layout.statik_row_one_line

    override fun type(twoTextRow: TwoTextRowItemRepresentable): Int = R.layout.statik_row_two_lines

    override fun viewHolder(type: Int, view: View): StatikViewHolder {
        return when (type) {
            R.layout.statik_text_supplementary -> TextSupplementaryViewHolder(view)
            R.layout.statik_view_placeholder -> ViewSupplementaryViewHolder(view)

            R.layout.statik_row_one_line -> TextRowViewHolder(view)
            R.layout.statik_row_two_lines -> TwoTextRowViewHolder(view)
            else -> error("You should not reach here")
        }
    }
}