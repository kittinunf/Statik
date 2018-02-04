package com.github.kttinunf.statik

import com.github.kittinunf.statik.dsl.row
import com.github.kittinunf.statik.model.Row
import io.kotlintest.matchers.instanceOf
import io.kotlintest.matchers.shouldEqual
import io.kotlintest.specs.StringSpec

class DSLTest : StringSpec() {

    init {
        "row command should produce correct row with Text type" {
            val r = row {
                primaryText = "Foo"
            }

            r.type shouldEqual instanceOf(Row.Type.Text::class)

        }

        "row command should produce correct row value 1" {
            val r = row {
                primaryText = "Foo"
            }

            val type = r.type as Row.Type.Text

            type.primaryText shouldEqual "Foo"
        }

        "row command should produce correct row value 2" {
            val r = row {
                primaryText = "Foo"
                secondaryText = "Bar"
            }

            val type = r.type as Row.Type.Text

            type.primaryText shouldEqual "Foo"
            type.secondaryText shouldEqual "Barr"
        }
    }
}
