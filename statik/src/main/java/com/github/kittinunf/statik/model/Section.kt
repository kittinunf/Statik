package com.github.kittinunf.statik.model

import com.github.kittinunf.statik.representable.ItemRepresentable

data class Section(val header: Accessory? = null,
                   val rows: List<Row>,
                   val representables: List<ItemRepresentable>,
                   val footer: Accessory? = null
)