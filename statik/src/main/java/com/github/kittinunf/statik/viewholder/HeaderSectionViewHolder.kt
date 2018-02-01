package com.github.kittinunf.statik.viewholder

import android.view.View
import com.github.kittinunf.statik.R
import com.github.kittinunf.statik.representable.HeaderSectionItemRepresentable

class HeaderSectionViewHolder(view: View) : HeaderFooterSectionViewHolder(view),
        BindableViewHolder<HeaderSectionItemRepresentable> {

    override fun bind(item: HeaderSectionItemRepresentable) {
        bindWithAccessory(R.layout.statik_text_header, item.header)
    }
}