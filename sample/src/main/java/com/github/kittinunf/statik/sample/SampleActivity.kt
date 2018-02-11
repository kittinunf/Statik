package com.github.kittinunf.statik.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.InputType
import android.view.View
import com.github.kittinunf.statik.dsl.statik
import com.github.kittinunf.statik.sample.util.find
import com.github.kittinunf.statik.sample.util.toast
import kotlinx.android.synthetic.main.activity_list.list

class SampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_list)

        val adapter =
                statik {
                    section {
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
                        }
                        row {
                            primaryText = "2 Lines"
                            secondaryText = "With custom widget"
                            iconRes = android.R.drawable.ic_btn_speak_now
                        }
                    }
                    section {
                        row {
                            primaryText = "Adjustable Primary"
                            secondaryText = "Adjustable Secondary"
                        }
                        row {
                            primaryText = "Clickable row"
                            secondaryText = "For toast"
                            clickHandler = {
                                toast("You did good!")
                            }
                        }
                        row {
                            layoutRes = R.layout.widget_button
                            configuration = {
                                it.find<View>(android.R.id.button1).setOnClickListener {
                                    toast("You click on button")
                                }
                            }
                        }
                        row {
                            hint = "Username"
                            primaryText = "kittinunf"
                            inputType = InputType.TYPE_CLASS_TEXT
                        }
                        row {
                            hint = "Password"
                            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                        }
                        row {
                            hint = "Confirm Password"
                            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                        }
                    }
                }

        list.also {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = adapter
        }
    }
}