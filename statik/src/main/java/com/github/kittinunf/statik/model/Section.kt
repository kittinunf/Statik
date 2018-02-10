package com.github.kittinunf.statik.model

import com.github.kittinunf.statik.representable.ItemRepresentable

data class Section(val rows: List<Row>,
                   val headerRepresentable : ItemRepresentable? = null,
                   val representables: List<ItemRepresentable>,
                   val footerRepresentable : ItemRepresentable? = null
)