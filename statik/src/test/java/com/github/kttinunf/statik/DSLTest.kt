package com.github.kttinunf.statik

import com.github.kittinunf.statik.dsl.row
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

        assertThat(t, isA(Row.Type.Text::class.java))
        assertThat(t.primaryText, equalTo("foo"))
        assertThat(t.secondaryText, equalTo("bar"))
    }

    @Test
    fun rowCommand_shouldBuildTextTypeWithCorrectAttribute() {
        val r = row {
            primaryText = "foo"
            primaryTextAttribute {
                color = "#112233"
            }

            secondaryTextAttribute {
                color = "#223344"
            }
        }

        val t = r.type as Row.Type.Text

        assertThat(t.primaryTextAttribute?.textColor, equalTo("#112233"))
        assertThat(t.secondaryTextAttribute?.textColor, equalTo("#112233"))
    }

}