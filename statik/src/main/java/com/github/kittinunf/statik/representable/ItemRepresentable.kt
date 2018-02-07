package com.github.kittinunf.statik.representable

import com.github.kittinunf.statik.adapter.TypeFactory
import com.github.kittinunf.statik.model.Accessory
import com.github.kittinunf.statik.model.Row
import com.github.kittinunf.statik.model.Section
import com.github.kittinunf.statik.model.TextRow
import kotlin.properties.Delegates

interface ItemRepresentable {
    fun type(typeFactory: TypeFactory): Int
}

data class HeaderSectionItemRepresentable(private val section: Section) : ItemRepresentable {

    val header: Accessory
        get() = section.header ?: error("Header must not be null in HeaderSectionItem")

    override fun type(typeFactory: TypeFactory): Int = typeFactory.type(this)
}

data class RowItemRepresentable(val row: Row) : ItemRepresentable {
    override fun type(typeFactory: TypeFactory): Int = typeFactory.type(this)
}

data class FooterSectionItemRepresentable(private val section: Section) : ItemRepresentable {

    val footer: Accessory
        get() = section.footer ?: error("Footer must not be null in FooterSectionItem")

    override fun type(typeFactory: TypeFactory): Int = typeFactory.type(this)
}

data class TextRowItemRepresentable(val row: TextRow) : ItemRepresentable {

    var onChangedListener: ((TextRow) -> Unit)? = null

    var value by Delegates.observable(row.value) { _, old, new ->
        if (old != new) {
            row.value = new
            onChangedListener?.invoke(row)
        }
    }

    override fun type(typeFactory: TypeFactory): Int = typeFactory.type(this)
}