package com.github.kittinunf.statik.adapter

import android.view.View
import com.github.kittinunf.statik.R
import com.github.kittinunf.statik.representable.TextRowItemRepresentable
import com.github.kittinunf.statik.representable.TextSupplementaryItemRepresentable
import com.github.kittinunf.statik.representable.TwoTextRowItemRepresentable
import com.github.kittinunf.statik.representable.ViewRowItemRepresentable
import com.github.kittinunf.statik.representable.ViewSupplementaryItemRepresentable
import com.github.kittinunf.statik.viewholder.StatikViewHolder
import com.github.kittinunf.statik.viewholder.TextRowViewHolder
import com.github.kittinunf.statik.viewholder.TextSupplementaryViewHolder
import com.github.kittinunf.statik.viewholder.TwoTextRowViewHolder
import com.github.kittinunf.statik.viewholder.ViewRowViewHolder
import com.github.kittinunf.statik.viewholder.ViewSupplementaryViewHolder

interface TypeFactory {

    fun type(supplementary: TextSupplementaryItemRepresentable): Int
    fun type(viewSupplementary: ViewSupplementaryItemRepresentable): Int

    fun type(textRow: TextRowItemRepresentable): Int
    fun type(twoTextRow: TwoTextRowItemRepresentable): Int
    fun type(viewRow: ViewRowItemRepresentable): Int

    fun viewHolder(type: Int, view: View): StatikViewHolder
}

internal val defaultTypeFactory = object : TypeFactory {

    override fun type(supplementary: TextSupplementaryItemRepresentable): Int = R.layout.statik_text_supplementary
    override fun type(viewSupplementary: ViewSupplementaryItemRepresentable): Int = R.layout.statik_view_supplementary

    override fun type(textRow: TextRowItemRepresentable): Int = R.layout.statik_text_one_line_row
    override fun type(twoTextRow: TwoTextRowItemRepresentable): Int = R.layout.statik_text_two_line_row
    override fun type(viewRow: ViewRowItemRepresentable): Int = R.layout.statik_view_row

    override fun viewHolder(type: Int, view: View): StatikViewHolder {
        return when (type) {
            R.layout.statik_text_supplementary -> TextSupplementaryViewHolder(view)
            R.layout.statik_view_supplementary -> ViewSupplementaryViewHolder(view)

            R.layout.statik_text_one_line_row -> TextRowViewHolder(view)
            R.layout.statik_text_two_line_row -> TwoTextRowViewHolder(view)
            R.layout.statik_view_row -> ViewRowViewHolder(view)
            else -> error("You should not reach here")
        }
    }
}