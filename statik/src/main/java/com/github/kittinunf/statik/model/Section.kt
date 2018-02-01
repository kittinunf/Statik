package com.github.kittinunf.statik.model

data class Section(val header: Accessory? = null,
                   val rows: List<Row>,
                   val footer: Accessory? = null
)