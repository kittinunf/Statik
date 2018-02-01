package com.github.kittinunf.statik.viewholder

import android.view.View
import com.github.kittinunf.statik.R
import com.github.kittinunf.statik.representable.FooterSectionItemRepresentable

class FooterSectionViewHolder(view: View) : HeaderFooterSectionViewHolder(view),
        BindableViewHolder<FooterSectionItemRepresentable> {

    override fun bind(item: FooterSectionItemRepresentable) {
        bindWithAccessory(R.layout.statik_text_footer, item.footer)
    }
}