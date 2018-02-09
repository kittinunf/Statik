package com.github.kittinunf.statik.dsl

import android.graphics.Typeface
import android.support.annotation.DrawableRes
import android.support.annotation.LayoutRes
import android.text.InputType
import android.view.Gravity
import com.github.kittinunf.statik.adapter.StatikAdapter
import com.github.kittinunf.statik.model.Accessory
import com.github.kittinunf.statik.model.ClickHandler
import com.github.kittinunf.statik.model.Row
import com.github.kittinunf.statik.model.Section
import com.github.kittinunf.statik.model.TextAttribute
import com.github.kittinunf.statik.model.ViewConfiguration
import com.github.kittinunf.statik.representable.ItemRepresentable
import com.github.kittinunf.statik.representable.TextRowItemRepresentable
import com.github.kittinunf.statik.representable.TwoTextRowItemRepresentable

class TextAttributeBuilder {

    var color: String? = null

    var sizeSP: Float? = null

    var typeface: Typeface = Typeface.DEFAULT

    var gravity: Int = Gravity.START

    fun build(): TextAttribute = TextAttribute(color, sizeSP, typeface, gravity)
}

class RowBuilder {

    var primaryText: String = ""

    private var primaryTextAttribute: TextAttribute? = null

    var secondaryText: String? = null

    private var secondaryTextAttribute: TextAttribute? = null

    private var type: Row.Type? = null

    var hint: String? = null

    var inputType: Int = InputType.TYPE_CLASS_TEXT

    @LayoutRes
    var layoutRes: Int? = null

    @DrawableRes
    var iconRes: Int? = null

    private var accessory: Accessory? = null

    var clickHandler: ClickHandler? = null

    var configuration: ViewConfiguration? = null

    fun primaryTextAttribute(block: TextAttributeBuilder.() -> Unit) {
        val builder = TextAttributeBuilder()
        builder.block()
        primaryTextAttribute = builder.build()
    }

    fun secondaryTextAttribute(block: TextAttributeBuilder.() -> Unit) {
        val builder = TextAttributeBuilder()
        builder.block()
        secondaryTextAttribute = builder.build()
    }

    fun accessory(block: AccessoryBuilder.() -> Unit) {
        val builder = AccessoryBuilder()
        builder.block()
        accessory = builder.build()
    }

    private fun buildType(): Row.Type {
        val res = layoutRes
        return if (res != null) {
            Row.Type.Custom(res, configuration)
        } else if (hint != null) {
            Row.Type.InputText(hint ?: "", primaryText, inputType)
        } else {
            Row.Type.Text(primaryText, primaryTextAttribute, secondaryText, secondaryTextAttribute)
        }
    }

    fun build(): Row {
        type = buildType()
        return Row(type, iconRes, accessory, clickHandler)
    }
}

class AccessoryBuilder {

    var text = ""

    private var textAttribute: TextAttribute? = null

    @LayoutRes
    var layoutRes: Int? = null

    var configuration: ViewConfiguration? = null

    fun textAttribute(block: TextAttributeBuilder.() -> Unit) {
        val builder = TextAttributeBuilder()
        builder.block()
        textAttribute = builder.build()
    }

    fun build(): Accessory {
        val res = layoutRes
        return if (res != null) {
            Accessory.Custom(res, configuration)
        } else {
            Accessory.Title(text, textAttribute, configuration)
        }
    }
}

class SectionBuilder {

    private var header: Accessory? = null

    private var footer: Accessory? = null

    private val rows = mutableListOf<Row>()
    private val representables = mutableListOf<ItemRepresentable>()

    fun row(block: RowBuilder.() -> Unit) {
        val builder = RowBuilder()
        builder.block()
        rows += builder.build()
    }

    fun rows(vararg r: Row) {
        rows += r.asList()
    }

    fun rows(vararg r: ItemRepresentable) {
        representables += r.asList()
    }

    fun header(block: AccessoryBuilder.() -> Unit) {
        val builder = AccessoryBuilder()
        builder.block()
        header = builder.build()
    }

    fun footer(block: AccessoryBuilder.() -> Unit) {
        val builder = AccessoryBuilder()
        builder.block()
        footer = builder.build()
    }

    fun build(): Section = Section(header, rows, representables, footer)
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

//class TextRowItemBuilder {
//
//    var text: String = ""
//
//    @DrawableRes
//    var iconRes: Int? = null
//
//    var onSetupListener: OnSetupListener? = null
//
//    var onClickListener: OnClickListener<TextRowItemRepresentable>? = null
//
//    fun build(): TextRowItemRepresentable =
//            TextRowItemRepresentable(TextRow(text, iconRes)).also {
//                it.onSetupListener = onSetupListener
//                it.onClickListener = onClickListener
//            }
//}
//
//class TwoTextRowItemBuilder {
//
//    var titleText: String = ""
//
//    var summaryText: String = ""
//
//    @DrawableRes
//    var iconRes: Int? = null
//
//    var onSetupListener: OnSetupListener? = null
//
//    var onClickListener: OnClickListener<TwoTextRowItemRepresentable>? = null
//
//    fun build(): TwoTextRowItemRepresentable =
//            TwoTextRowItemRepresentable(TwoTextRow(titleText, summaryText, iconRes)).also {
//                it.onSetupListener = onSetupListener
//                it.onClickListener = onClickListener
//            }
//}

fun textRow(block: TextRowItemRepresentable.() -> Unit): TextRowItemRepresentable {
    return TextRowItemRepresentable().apply(block)
}

fun twoTextRow(block: TwoTextRowItemRepresentable.() -> Unit): TwoTextRowItemRepresentable {
    return TwoTextRowItemRepresentable().apply(block)
}
