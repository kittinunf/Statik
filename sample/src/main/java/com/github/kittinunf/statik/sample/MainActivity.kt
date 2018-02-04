package com.github.kittinunf.statik.sample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.github.kittinunf.statik.dsl.row
import com.github.kittinunf.statik.dsl.section
import com.github.kittinunf.statik.dsl.statik
import kotlinx.android.synthetic.main.activity_list.list

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_list)

        val r1 = row {
            primaryText = "Simple List"
        }
        val r2 = row {
            primaryText = "Simple List"
            secondaryText = "With icon"
        }
        val r3 = row {
            primaryText = "Simple List"
            secondaryText = "With custom widget"
        }

        val s1 = section { rows(r1, r2, r3) }

        val rf = row {
            primaryText = "Example 1"
            clickHandler = {
                startActivity(Intent(this@MainActivity, SampleActivity::class.java))
            }
        }

        val s2 = section {
            header {
                text = "Full Example"
            }
            rows(rf)
        }

        val adapter =
                statik {
                    sections(s1, s2)
                }

        list.also {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = adapter
        }
    }
}