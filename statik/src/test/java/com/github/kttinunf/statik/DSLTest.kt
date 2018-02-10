package com.github.kttinunf.statik

import android.text.InputType
import com.github.kittinunf.statik.dsl.row
import com.github.kittinunf.statik.dsl.textFooter
import com.github.kittinunf.statik.dsl.textHeader
import com.github.kittinunf.statik.dsl.textRow
import com.github.kittinunf.statik.dsl.twoTextRow
import com.github.kittinunf.statik.model.Row
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.isA
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class DSLTest {

    @Test
    fun rowCommand_shouldBuildTextType() {
        val r = row {
            primaryText = "foo"
        }

        assertThat(r.type as Row.Type.Text, isA(Row.Type.Text::class.java))
    }

    @Test
    fun rowCommand_shouldBuildTextTypeWithCorrectValue() {
        val r = row {
            primaryText = "foo"
            secondaryText = "bar"
        }

        val t = r.type as Row.Type.Text

        assertThat(t.primaryText, equalTo("foo"))
        assertThat(t.secondaryText, equalTo("bar"))
    }

    @Test
    fun rowCommand_shouldBuildEditTextType() {
        val r = row {
            hint = "heyhey"
        }

        val t = r.type as Row.Type.InputText

        assertThat(t, isA(Row.Type.InputText::class.java))
    }

    @Test
    fun rowCommand_shouldBuildEditTextTypeWithCorrectValue() {
        val r = row {
            hint = "heyhey"
            inputType = InputType.TYPE_CLASS_PHONE
        }

        val t = r.type as Row.Type.InputText

        assertThat(t.hint, equalTo("heyhey"))
        assertThat(t.inputType, equalTo(InputType.TYPE_CLASS_PHONE))
        assertThat(t.text, equalTo(""))
    }

    @Test
    fun rowCommand_shouldBuildCustomType() {
        val r = row {
            layoutRes = 1
        }

        val t = r.type as Row.Type.Custom

        assertThat(t, isA(Row.Type.Custom::class.java))
    }

    @Test
    fun rowCommand_shouldBuildCustomTypeWithCorrectValue() {
        val r = row {
            layoutRes = 1
        }

        val t = r.type as Row.Type.Custom

        assertThat(t.layoutRes, equalTo(1))
    }


    @Test
    fun textRowCommand() {
        val r = textRow {
            text = "Foo"
        }

        assertThat(r.text, equalTo("Foo"))
    }

    @Test
    fun textRowCommandWithIcon() {
        val r = textRow {
            text = "Foo"
            iconRes = 11
        }

        assertThat(r.text, equalTo("Foo"))
        assertThat(r.iconRes, equalTo(11))
    }

    @Test
    fun twoTextRowCommand() {
        val r = twoTextRow {
            titleText = "FooFoo"
            summaryText = "Barbar"
        }

        assertThat(r.titleText, equalTo("FooFoo"))
        assertThat(r.summaryText, equalTo("Barbar"))
    }

    @Test
    fun twoTextRowCommandWithIcon() {
        val r = twoTextRow {
            titleText = "FooFoo"
            summaryText = "Barbar"

            iconRes = 123
        }

        assertThat(r.titleText, equalTo("FooFoo"))
        assertThat(r.summaryText, equalTo("Barbar"))
        assertThat(r.iconRes, equalTo(123))
    }

    @Test
    fun textHeaderCommand() {
        val r = textHeader {
            text = "FooFoo"
            layoutRes = 111
        }

        assertThat(r.text, equalTo("FooFoo"))
        assertThat(r.layoutRes, equalTo(111))
    }

    @Test
    fun textFooterCommand() {
        val r = textFooter {
            text = "FooFoo"
            layoutRes = 111
        }

        assertThat(r.text, equalTo("FooFoo"))
        assertThat(r.layoutRes, equalTo(111))
    }
}
