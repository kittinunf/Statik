package com.github.kittinunf.statik.adapter

import android.view.View
import com.github.kittinunf.statik.R
import com.github.kittinunf.statik.representable.RowItemRepresentable
import com.github.kittinunf.statik.representable.TextRowItemRepresentable
import com.github.kittinunf.statik.representable.TextSupplementaryItemRepresentable
import com.github.kittinunf.statik.representable.TwoTextRowItemRepresentable
import com.github.kittinunf.statik.viewholder.RowViewHolder
import com.github.kittinunf.statik.viewholder.StatikViewHolder
import com.github.kittinunf.statik.viewholder.TextRowViewHolder
import com.github.kittinunf.statik.viewholder.TextSupplementaryViewHolder
import com.github.kittinunf.statik.viewholder.TwoTextRowViewHolder

interface TypeFactory {

    fun type(row: RowItemRepresentable): Int

    fun type(header: TextSupplementaryItemRepresentable): Int
    fun type(textRow: TextRowItemRepresentable): Int
    fun type(twoTextRow: TwoTextRowItemRepresentable): Int

    fun viewHolder(type: Int, view: View): StatikViewHolder
}

internal val defaultTypeFactory = object : TypeFactory {

    override fun type(row: RowItemRepresentable): Int = R.layout.statik_placeholder_row

    override fun type(header: TextSupplementaryItemRepresentable): Int = R.layout.statik_text_supplementary

    override fun type(textRow: TextRowItemRepresentable): Int = R.layout.statik_row_one_line

    override fun type(twoTextRow: TwoTextRowItemRepresentable): Int = R.layout.statik_row_two_lines

    override fun viewHolder(type: Int, view: View): StatikViewHolder {
        return when (type) {
            R.layout.statik_placeholder_row -> RowViewHolder(view)

            R.layout.statik_text_supplementary -> TextSupplementaryViewHolder(view)
            R.layout.statik_row_one_line -> TextRowViewHolder(view)
            R.layout.statik_row_two_lines -> TwoTextRowViewHolder(view)
            else -> error("You should not reach here")
        }
    }
}