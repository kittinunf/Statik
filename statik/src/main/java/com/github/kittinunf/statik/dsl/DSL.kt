package com.github.kittinunf.statik.dsl

import com.github.kittinunf.statik.adapter.StatikAdapter
import com.github.kittinunf.statik.model.Section
import com.github.kittinunf.statik.representable.*

class SectionBuilder {

    private var header: ItemRepresentable? = null

    private val rows = mutableListOf<ItemRepresentable>()

    private var footer: ItemRepresentable? = null

    private var valuesInSectionChangedListener: ((ItemRepresentable) -> Unit)? = null

    fun rows(vararg item: ItemRepresentable) {
        rows += item.asList()
    }

    fun rows(items: List<ItemRepresentable>) {
        rows += items
    }

    fun header(item: ItemRepresentable) {
        header = item
    }

    fun footer(item: ItemRepresentable) {
        footer = item
    }

    fun onValuesInSectionChangedListener(listener: (ItemRepresentable) -> Unit) {
        valuesInSectionChangedListener = listener
    }

    fun build(): Section =
            Section(header, rows, footer).apply {
                onValuesInSectionChangedListener = valuesInSectionChangedListener
            }
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

fun textHeader(block: TextSupplementaryRepresentable.() -> Unit): HeaderTextSupplementaryRepresentable =
        HeaderTextSupplementaryRepresentable().apply(block)

fun textFooter(block: TextSupplementaryRepresentable.() -> Unit): FooterTextSupplementaryRepresentable =
        FooterTextSupplementaryRepresentable().apply(block)

fun viewHeader(block: ViewSupplementaryRepresentable.() -> Unit): HeaderViewSupplementaryRepresentable =
        HeaderViewSupplementaryRepresentable().apply(block)

fun viewFooter(block: ViewSupplementaryRepresentable.() -> Unit): FooterViewSupplementaryRepresentable =
        FooterViewSupplementaryRepresentable().apply(block)

fun buttonHeader(block: ButtonSupplementaryRepresentable.() -> Unit): ButtonSupplementaryRepresentable =
        ButtonSupplementaryRepresentable().apply(block)

fun buttonFooter(block: ButtonSupplementaryRepresentable.() -> Unit): ButtonSupplementaryRepresentable =
        ButtonSupplementaryRepresentable().apply(block)

fun textRow(block: TextRowRepresentable.() -> Unit): TextRowRepresentable =
        TextRowRepresentable().apply(block)

fun twoTextRow(block: TwoTextRowRepresentable.() -> Unit): TwoTextRowRepresentable =
        TwoTextRowRepresentable().apply(block)

fun viewRow(block: ViewRowRepresentable.() -> Unit): ViewRowRepresentable =
        ViewRowRepresentable().apply(block)

fun checkRow(block: CheckRowRepresentable.() -> Unit): CheckRowRepresentable =
        CheckRowRepresentable().apply(block)

fun switchRow(block: SwitchRowRepresentable.() -> Unit): SwitchRowRepresentable =
        SwitchRowRepresentable().apply(block)

fun inputRow(block: InputRowRepresentable.() -> Unit): InputRowRepresentable =
        InputRowRepresentable().apply(block)

fun spinnerRow(block: SpinnerRowRepresentable.() -> Unit): SpinnerRowRepresentable =
        SpinnerRowRepresentable().apply(block)

fun dateRow(block: DateRowRepresentable.() -> Unit): DateRowRepresentable =
        DateRowRepresentable().apply(block)

fun buttonRow(block: ButtonRowRepresentable.() -> Unit): ButtonRowRepresentable =
        ButtonRowRepresentable().apply(block)
