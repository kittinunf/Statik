package com.github.kittinunf.statik.adapter

import android.view.View
import com.github.kittinunf.statik.R
import com.github.kittinunf.statik.representable.CheckRowRepresentable
import com.github.kittinunf.statik.representable.FooterTextSupplementaryRepresentable
import com.github.kittinunf.statik.representable.FooterViewSupplementaryRepresentable
import com.github.kittinunf.statik.representable.HeaderTextSupplementaryRepresentable
import com.github.kittinunf.statik.representable.HeaderViewSupplementaryRepresentable
import com.github.kittinunf.statik.representable.InputRowRepresentable
import com.github.kittinunf.statik.representable.TextRowRepresentable
import com.github.kittinunf.statik.representable.TwoTextRowRepresentable
import com.github.kittinunf.statik.representable.ViewRowRepresentable
import com.github.kittinunf.statik.viewholder.CheckRowViewHolder
import com.github.kittinunf.statik.viewholder.FooterTextSupplementaryViewHolder
import com.github.kittinunf.statik.viewholder.HeaderTextSupplementaryViewHolder
import com.github.kittinunf.statik.viewholder.InputRowViewHolder
import com.github.kittinunf.statik.viewholder.StatikViewHolder
import com.github.kittinunf.statik.viewholder.TextRowViewHolder
import com.github.kittinunf.statik.viewholder.TwoTextRowViewHolder
import com.github.kittinunf.statik.viewholder.ViewRowViewHolder
import com.github.kittinunf.statik.viewholder.ViewSupplementaryViewHolder

interface TypeFactory {

    fun type(header: HeaderTextSupplementaryRepresentable): Int
    fun type(header: HeaderViewSupplementaryRepresentable): Int
    fun type(footer: FooterTextSupplementaryRepresentable): Int
    fun type(footer: FooterViewSupplementaryRepresentable): Int

    fun type(textRow: TextRowRepresentable): Int
    fun type(twoTextRow: TwoTextRowRepresentable): Int
    fun type(checkRow: CheckRowRepresentable): Int
    fun type(viewRow: ViewRowRepresentable): Int
    fun type(inputRow: InputRowRepresentable): Int

    fun viewHolder(type: Int, view: View): StatikViewHolder
}

internal val defaultTypeFactory = object : TypeFactory {

    override fun type(header: HeaderTextSupplementaryRepresentable): Int = R.layout.statik_text_header_supplementary
    override fun type(header: HeaderViewSupplementaryRepresentable): Int = R.layout.statik_view_header_supplementary
    override fun type(footer: FooterTextSupplementaryRepresentable): Int = R.layout.statik_text_footer_supplementary
    override fun type(footer: FooterViewSupplementaryRepresentable): Int = R.layout.statik_view_footer_supplementary

    override fun type(textRow: TextRowRepresentable): Int = R.layout.statik_text_one_line_row
    override fun type(twoTextRow: TwoTextRowRepresentable): Int = R.layout.statik_text_two_line_row
    override fun type(checkRow: CheckRowRepresentable): Int = R.layout.statik_check_two_line_row
    override fun type(inputRow: InputRowRepresentable): Int = R.layout.statik_text_input_row
    override fun type(viewRow: ViewRowRepresentable): Int = R.layout.statik_view_row

    override fun viewHolder(type: Int, view: View): StatikViewHolder {
        return when (type) {
            R.layout.statik_text_header_supplementary -> HeaderTextSupplementaryViewHolder(view)
            R.layout.statik_view_header_supplementary -> ViewSupplementaryViewHolder(view)
            R.layout.statik_text_footer_supplementary -> FooterTextSupplementaryViewHolder(view)
            R.layout.statik_view_footer_supplementary -> ViewSupplementaryViewHolder(view)

            R.layout.statik_text_one_line_row -> TextRowViewHolder(view)
            R.layout.statik_text_two_line_row -> TwoTextRowViewHolder(view)
            R.layout.statik_check_two_line_row -> CheckRowViewHolder(view)
            R.layout.statik_text_input_row -> InputRowViewHolder(view)
            R.layout.statik_view_row -> ViewRowViewHolder(view)
            else -> error("You should not reach here")
        }
    }
}