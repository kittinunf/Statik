package com.github.kittinunf.statik.sample

import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import com.github.kittinunf.statik.adapter.StatikAdapter
import com.github.kittinunf.statik.model.Accessory
import com.github.kittinunf.statik.model.Row
import com.github.kittinunf.statik.model.Section
import com.github.kittinunf.statik.model.TextAttribute
import kotlinx.android.synthetic.main.activity_sample.list

class SampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_sample)

        list.also {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = StatikAdapter().apply {
                sections = listOf(
                        Section(header = Accessory.Title("Feature",
                                TextAttribute("#ff4081", resources.getDimension(R.dimen.text_20))),
                                rows = listOf(
                                        Row(primaryText = "Simple Text"),
                                        Row(primaryText = "2 Lines Text", secondaryText = "Text"),
                                        Row(primaryText = "Text", secondaryText = "With icon", iconRes = android.R.drawable.ic_delete),
                                        Row(primaryText = "Text", secondaryText = "With accessory text",
                                                iconRes = android.R.drawable.ic_btn_speak_now,
                                                accessory = Accessory.Title("Confirmed", TextAttribute("#dc0030"))),
                                        Row(primaryText = "Text", secondaryText = "With custom accessory widget",
                                                accessory = Accessory.Custom(R.layout.widget_checkbox, viewConfiguration = {
                                                    it.findViewById<CheckBox>(android.R.id.checkbox).setOnCheckedChangeListener { _, isChecked ->
                                                        Toast.makeText(this@SampleActivity, "Checked is $isChecked", Toast.LENGTH_SHORT).show()
                                                    }
                                                }))
                                ),
                                footer = Accessory.Title("Footer",
                                        TextAttribute("#333333",
                                                resources.getDimension(R.dimen.text_18))
                                )
                        ),
                        Section(header = Accessory.Title("Header",
                                TextAttribute("#999999", resources.getDimension(R.dimen.text_24))),
                                rows = listOf(
                                        Row(primaryText = "Click here for toast", secondaryText = "Hello",
                                                clickHandler = {
                                                    Toast.makeText(this@SampleActivity, "Hello", Toast.LENGTH_SHORT).show()
                                                }),
                                        Row(primaryText = "Click here for another toast", secondaryText = "World",
                                                clickHandler = {
                                                    Toast.makeText(this@SampleActivity, "World", Toast.LENGTH_SHORT).show()
                                                })
                                ),
                                footer = Accessory.Title("Visit www.google.com >",
                                        TextAttribute("#dc0030", resources.getDimension(R.dimen.text_14), textGravity = Gravity.END),
                                        viewConfiguration = {
                                            it.findViewById<View>(android.R.id.title).setOnClickListener {
                                                openBrowser("https://www.google.com")
                                            }
                                        })
                        ),
                        Section(rows = emptyList(),
                                footer = Accessory.Title("world3",
                                        TextAttribute("#666666", resources.getDimension(R.dimen.text_20), typeface = Typeface.DEFAULT_BOLD))
                        )
                )
            }
        }
    }

    private fun openBrowser(url: String) {
        val i = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
        }
        startActivity(i)
    }
}