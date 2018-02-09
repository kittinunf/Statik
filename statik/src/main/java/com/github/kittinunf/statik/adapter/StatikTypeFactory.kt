package com.github.kittinunf.statik.adapter

import android.view.View
import com.github.kittinunf.statik.R
import com.github.kittinunf.statik.representable.FooterSectionItemRepresentable
import com.github.kittinunf.statik.representable.HeaderSectionItemRepresentable
import com.github.kittinunf.statik.representable.RowItemRepresentable
import com.github.kittinunf.statik.representable.TextRowItemRepresentable
import com.github.kittinunf.statik.representable.TextSupplementaryItemRepresentable
import com.github.kittinunf.statik.representable.TwoTextRowItemRepresentable
import com.github.kittinunf.statik.viewholder.FooterSectionViewHolder
import com.github.kittinunf.statik.viewholder.HeaderSectionViewHolder
import com.github.kittinunf.statik.viewholder.RowViewHolder
import com.github.kittinunf.statik.viewholder.StatikViewHolder
import com.github.kittinunf.statik.viewholder.TextRowViewHolder
import com.github.kittinunf.statik.viewholder.TextSupplementaryViewHolder
import com.github.kittinunf.statik.viewholder.TwoTextRowViewHolder

interface TypeFactory {

    fun type(section: HeaderSectionItemRepresentable): Int
    fun type(row: RowItemRepresentable): Int
    fun type(section: FooterSectionItemRepresentable): Int

    fun type(header: TextSupplementaryItemRepresentable): Int
    fun type(textRow: TextRowItemRepresentable): Int
    fun type(twoTextRow: TwoTextRowItemRepresentable): Int

    fun viewHolder(type: Int, view: View): StatikViewHolder
}

internal val defaultTypeFactory = object : TypeFactory {

    override fun type(section: HeaderSectionItemRepresentable): Int = R.layout.statik_placeholder_header

    override fun type(row: RowItemRepresentable): Int = R.layout.statik_placeholder_row

    override fun type(section: FooterSectionItemRepresentable): Int = R.layout.statik_placeholder_footer

    override fun type(header: TextSupplementaryItemRepresentable): Int = R.layout.statik_text_supplementary

    override fun type(textRow: TextRowItemRepresentable): Int = R.layout.statik_row_one_line

    override fun type(twoTextRow: TwoTextRowItemRepresentable): Int = R.layout.statik_row_two_lines

    override fun viewHolder(type: Int, view: View): StatikViewHolder {
        return when (type) {
            R.layout.statik_placeholder_header -> HeaderSectionViewHolder(view)
            R.layout.statik_placeholder_footer -> FooterSectionViewHolder(view)
            R.layout.statik_placeholder_row -> RowViewHolder(view)

            R.layout.statik_text_supplementary -> TextSupplementaryViewHolder(view)
            R.layout.statik_row_one_line -> TextRowViewHolder(view)
            R.layout.statik_row_two_lines -> TwoTextRowViewHolder(view)
            else -> error("You should not reach here")
        }
    }
}