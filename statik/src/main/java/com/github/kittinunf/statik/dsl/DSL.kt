package com.github.kittinunf.statik.dsl

import android.support.annotation.DrawableRes
import android.support.annotation.LayoutRes
import android.text.InputType
import android.view.View
import com.github.kittinunf.statik.adapter.StatikAdapter
import com.github.kittinunf.statik.model.ClickHandler
import com.github.kittinunf.statik.model.Row
import com.github.kittinunf.statik.model.Section
import com.github.kittinunf.statik.representable.ItemRepresentable
import com.github.kittinunf.statik.representable.TextRowItemRepresentable
import com.github.kittinunf.statik.representable.TextSupplementaryItemRepresentable
import com.github.kittinunf.statik.representable.TwoTextRowItemRepresentable
import com.github.kittinunf.statik.representable.ViewSupplementaryItemRepresentable

class RowBuilder {

    var primaryText: String = ""

    var secondaryText: String? = null

    private var type: Row.Type? = null

    var hint: String? = null

    var inputType: Int = InputType.TYPE_CLASS_TEXT

    @LayoutRes
    var layoutRes: Int? = null

    @DrawableRes
    var iconRes: Int? = null

    var clickHandler: ClickHandler? = null

    var configuration: ((View) -> Unit)? = null

    private fun buildType(): Row.Type {
        val res = layoutRes
        return if (res != null) {
            Row.Type.Custom(res, configuration)
        } else if (hint != null) {
            Row.Type.InputText(hint ?: "", primaryText, inputType)
        } else {
            Row.Type.Text(primaryText, secondaryText)
        }
    }

    fun build(): Row {
        type = buildType()
        return Row(type, iconRes, clickHandler)
    }
}

class SectionBuilder {

    private val rows = mutableListOf<Row>()

    private var headerRepresentable: ItemRepresentable? = null

    private val representables = mutableListOf<ItemRepresentable>()

    private var footerRepresentable: ItemRepresentable? = null

    fun row(block: RowBuilder.() -> Unit) {
        val builder = RowBuilder()
        builder.block()
        rows += builder.build()
    }

    fun rows(vararg item: ItemRepresentable) {
        representables += item.asList()
    }

    fun header(item: ItemRepresentable) {
        headerRepresentable = item
    }

    fun footer(item: ItemRepresentable) {
        footerRepresentable = item
    }

    fun build(): Section = Section(rows, headerRepresentable, representables, footerRepresentable)
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

fun row(block: RowBuilder.() -> Unit): Row {
    val builder = RowBuilder()
    builder.block()
    return builder.build()
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

fun textRow(block: TextRowItemRepresentable.() -> Unit): TextRowItemRepresentable =
        TextRowItemRepresentable().apply(block)

fun twoTextRow(block: TwoTextRowItemRepresentable.() -> Unit): TwoTextRowItemRepresentable =
        TwoTextRowItemRepresentable().apply(block)

fun textHeader(block: TextSupplementaryItemRepresentable.() -> Unit): TextSupplementaryItemRepresentable =
        TextSupplementaryItemRepresentable().apply(block)

fun textFooter(block: TextSupplementaryItemRepresentable.() -> Unit): TextSupplementaryItemRepresentable =
        TextSupplementaryItemRepresentable().apply(block)

fun viewHeader(block: ViewSupplementaryItemRepresentable.() -> Unit): ViewSupplementaryItemRepresentable =
        ViewSupplementaryItemRepresentable().apply(block)

fun viewFooter(block: ViewSupplementaryItemRepresentable.() -> Unit) : ViewSupplementaryItemRepresentable =
        ViewSupplementaryItemRepresentable().apply(block)
