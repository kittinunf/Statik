package com.github.kittinunf.statik.dsl

import com.github.kittinunf.statik.adapter.StatikAdapter
import com.github.kittinunf.statik.model.Section
import com.github.kittinunf.statik.representable.FooterTextSupplementaryRepresentable
import com.github.kittinunf.statik.representable.HeaderTextSupplementaryRepresentable
import com.github.kittinunf.statik.representable.ItemRepresentable
import com.github.kittinunf.statik.representable.TextRowItemRepresentable
import com.github.kittinunf.statik.representable.TextSupplementaryItemRepresentable
import com.github.kittinunf.statik.representable.TwoTextRowItemRepresentable
import com.github.kittinunf.statik.representable.ViewRowItemRepresentable
import com.github.kittinunf.statik.representable.ViewSupplementaryItemRepresentable

class SectionBuilder {

    private var header: ItemRepresentable? = null

    private val rows = mutableListOf<ItemRepresentable>()

    private var footer: ItemRepresentable? = null

    fun rows(vararg item: ItemRepresentable) {
        rows += item.asList()
    }

    fun header(item: ItemRepresentable) {
        header = item
    }

    fun footer(item: ItemRepresentable) {
        footer = item
    }

    fun build(): Section = Section(header, rows, footer)
}

class StatikBuilder {

    private val sections = mutableListOf<Section>()

    fun section(block: SectionBuilder.() -> Unit) {
        val builder = SectionBuilder()
        builder.block()
        sections += builder.build()
    }

    fun sections(vararg s: Section) {
        sections += s.asList()
    }

    fun build() = sections
}

fun section(block: SectionBuilder.() -> Unit): Section {
    val builder = SectionBuilder()
    builder.block()
    return builder.build()
}

fun statik(block: StatikBuilder.() -> Unit): StatikAdapter =
        StatikAdapter().apply {
            sections = sections(block)
        }

private fun sections(block: StatikBuilder.() -> Unit): List<Section> {
    val builder = StatikBuilder()
    builder.block()
    return builder.build()
}

fun textHeader(block: TextSupplementaryItemRepresentable.() -> Unit): HeaderTextSupplementaryRepresentable =
        HeaderTextSupplementaryRepresentable().apply(block)

fun textFooter(block: TextSupplementaryItemRepresentable.() -> Unit): FooterTextSupplementaryRepresentable =
        FooterTextSupplementaryRepresentable().apply(block)

fun viewHeader(block: ViewSupplementaryItemRepresentable.() -> Unit): ViewSupplementaryItemRepresentable =
        ViewSupplementaryItemRepresentable().apply(block)

fun viewFooter(block: ViewSupplementaryItemRepresentable.() -> Unit): ViewSupplementaryItemRepresentable =
        ViewSupplementaryItemRepresentable().apply(block)

fun textRow(block: TextRowItemRepresentable.() -> Unit): TextRowItemRepresentable =
        TextRowItemRepresentable().apply(block)

fun twoTextRow(block: TwoTextRowItemRepresentable.() -> Unit): TwoTextRowItemRepresentable =
        TwoTextRowItemRepresentable().apply(block)

fun viewRow(block: ViewRowItemRepresentable.() -> Unit): ViewRowItemRepresentable =
        ViewRowItemRepresentable().apply(block)
