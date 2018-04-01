package com.github.kittinunf.statik.model

import com.github.kittinunf.statik.representable.ItemRepresentable

data class Section(val header: ItemRepresentable? = null,
                   val rows: MutableList<ItemRepresentable>,
                   val footer: ItemRepresentable? = null) {

    var onValueInSectionChangedListener: ((ItemRepresentable) -> Unit)? = null
}
