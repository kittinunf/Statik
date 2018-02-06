package com.github.kttinunf.statik

import android.graphics.Typeface
import android.view.Gravity
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
            primaryTextAttribute {
                color = "#112233"
                sizeSP = 10.0f
                typeface = Typeface.DEFAULT_BOLD
                gravity = Gravity.START
            }

            secondaryTextAttribute {
                color = "#223344"
                sizeSP = 20.0f
                typeface = Typeface.SERIF
                gravity = Gravity.END
            }
        }

        val t = r.type as Row.Type.Text

        assertThat(t.primaryTextAttribute?.textColor, equalTo("#112233"))
        assertThat(t.secondaryTextAttribute?.textColor, equalTo("#223344"))
        assertThat(t.primaryTextAttribute?.textSizeSP, equalTo(10.0f))
        assertThat(t.secondaryTextAttribute?.textSizeSP, equalTo(20.0f))
        assertThat(t.primaryTextAttribute?.typeface, equalTo(Typeface.DEFAULT_BOLD))
        assertThat(t.secondaryTextAttribute?.typeface, equalTo(Typeface.SERIF))
        assertThat(t.primaryTextAttribute?.textGravity, equalTo(Gravity.START))
        assertThat(t.secondaryTextAttribute?.textGravity, equalTo(Gravity.END))
    }

}