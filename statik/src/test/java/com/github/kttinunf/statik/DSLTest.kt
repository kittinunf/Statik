package com.github.kttinunf.statik

import com.github.kittinunf.statik.dsl.textFooter
import com.github.kittinunf.statik.dsl.textHeader
import com.github.kittinunf.statik.dsl.textRow
import com.github.kittinunf.statik.dsl.twoTextRow
import com.github.kittinunf.statik.dsl.viewFooter
import com.github.kittinunf.statik.dsl.viewHeader
import com.github.kittinunf.statik.dsl.viewRow
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class DSLTest {

    @Test
    fun textRow() {
        val r = textRow {
            text = "Foo"
        }

        assertThat(r.text, equalTo("Foo"))
    }

    @Test
    fun textRowWithIcon() {
        val r = textRow {
            text = "Foo"
            iconRes = 11
        }

        assertThat(r.text, equalTo("Foo"))
        assertThat(r.iconRes, equalTo(11))
    }

    @Test
    fun twoTextRow() {
        val r = twoTextRow {
            titleText = "FooFoo"
            summaryText = "Barbar"
        }

        assertThat(r.titleText, equalTo("FooFoo"))
        assertThat(r.summaryText, equalTo("Barbar"))
    }

    @Test
    fun twoTextRowWithIcon() {
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
    fun textHeader() {
        val r = textHeader {
            text = "FooFoo"
            layoutRes = 111
        }

        assertThat(r.text, equalTo("FooFoo"))
        assertThat(r.layoutRes, equalTo(111))
    }

    @Test
    fun textFooter() {
        val r = textFooter {
            text = "FooFoo"
            layoutRes = 111
        }

        assertThat(r.text, equalTo("FooFoo"))
        assertThat(r.layoutRes, equalTo(111))
    }

    @Test
    fun viewHeader() {
        val h = viewHeader {
            layoutRes = 123
        }

        assertThat(h.layoutRes, equalTo(123))
    }

    @Test
    fun viewFooter() {
        val f = viewFooter {
            layoutRes = 321
        }

        assertThat(f.layoutRes, equalTo(321))
    }

    @Test
    fun viewRow() {
        val r = viewRow {
            layoutRes = 12345
        }

        assertThat(r.layoutRes, equalTo(12345))
    }
}
