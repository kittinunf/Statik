package com.github.kittinunf.statik.model

import com.github.kittinunf.statik.representable.ItemRepresentable

data class Section(val header: ItemRepresentable? = null,
                   val rows: List<ItemRepresentable>,
                   val footer: ItemRepresentable? = null)