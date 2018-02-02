package com.github.kittinunf.statik.sample

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import com.github.kittinunf.statik.adapter.StatikAdapter
import com.github.kittinunf.statik.dsl.statik
import kotlinx.android.synthetic.main.activity_sample.list

class SampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_sample)

        val section =
                statik {
                    section {
                        header {
                            text = "Features"
                            textAttribute {
                                color = "#ff4081"
                                sizeSP = resources.getDimension(R.dimen.text_20)
                            }
                        }
                        row {
                            primaryText = "Simple Text"
                        }
                        row {
                            primaryText = "Simple 2 Lines"
                            secondaryText = "Second"
                        }
                        row {
                            primaryText = "2 Lines"
                            secondaryText = "With Icon"
                            iconRes = android.R.drawable.ic_menu_call
                        }
                        row {
                            primaryText = "2 Lines"
                            secondaryText = "With Text accessory"
                            iconRes = android.R.drawable.ic_delete
                            accessory {
                                text = "Error"
                                textAttribute {
                                    color = "#dc0030"
                                    gravity = Gravity.END
                                }
                            }
                        }
                        row {
                            primaryText = "2 Lines"
                            secondaryText = "With custom widget"
                            iconRes = android.R.drawable.ic_btn_speak_now
                            accessory {
                                layoutRes = R.layout.widget_checkbox
                                configuration = {
                                    it.findViewById<CheckBox>(android.R.id.checkbox)
                                            .setOnCheckedChangeListener { _, checked ->
                                                Toast.makeText(this@SampleActivity,
                                                        "Checked is $checked",
                                                        Toast.LENGTH_SHORT)
                                                        .show()
                                            }
                                }
                            }
                        }
                        footer {
                            text = "Footer"
                            textAttribute {
                                color = "#333333"
                                sizeSP = resources.getDimension(R.dimen.text_18)
                            }
                        }
                    }
                    section {
                        header {
                            text = "Adjustable Header"
                            textAttribute {
                                color = "#999999"
                                sizeSP = resources.getDimension(R.dimen.text_24)
                            }
                        }
                        row {
                            primaryText = "Adjustable Primary"
                            primaryTextAttribute {
                                color = "#%06X".format(0xFFFFFF and resources.getColor(R.color.colorAccent))
                            }
                            secondaryText = "Adjustable Secondary"
                            secondaryTextAttribute {
                                color = "#%06X".format(0xFFFFFF and resources.getColor(android.R.color.holo_orange_dark))
                                sizeSP = resources.getDimension(R.dimen.text_22)
                            }
                        }
                        row {
                            primaryText = "Clickable row"
                            secondaryText = "For toast"
                            clickHandler = {
                                Toast.makeText(this@SampleActivity,
                                        "You did good!",
                                        Toast.LENGTH_SHORT)
                                        .show()
                            }
                        }
                        footer {
                            text = "Visit Google here >"
                            textAttribute {
                                gravity = Gravity.END
                            }
                            configuration = {
                                it.findViewById<View>(android.R.id.title).setOnClickListener {
                                    openBrowser("https://google.com")
                                }
                            }
                        }
                    }
                }

        list.also {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = StatikAdapter().apply {
                sections = section
            }
        }
    }

    private fun openBrowser(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
        })
    }

}