package com.github.kittinunf.statik.sample

import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.TextViewCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.InputType
import android.view.Gravity
import android.widget.CheckBox
import android.widget.ImageView
import com.github.kittinunf.statik.adapter.StatikAdapter
import com.github.kittinunf.statik.dsl.buttonFooter
import com.github.kittinunf.statik.dsl.buttonRow
import com.github.kittinunf.statik.dsl.checkRow
import com.github.kittinunf.statik.dsl.switchRow
import com.github.kittinunf.statik.dsl.dateRow
import com.github.kittinunf.statik.dsl.inputRow
import com.github.kittinunf.statik.dsl.section
import com.github.kittinunf.statik.dsl.spinnerRow
import com.github.kittinunf.statik.dsl.statik
import com.github.kittinunf.statik.dsl.textFooter
import com.github.kittinunf.statik.dsl.textHeader
import com.github.kittinunf.statik.dsl.textRow
import com.github.kittinunf.statik.dsl.twoTextRow
import com.github.kittinunf.statik.dsl.viewRow
import com.github.kittinunf.statik.sample.R.id.list
import com.github.kittinunf.statik.sample.util.find
import com.github.kittinunf.statik.sample.util.isValidEmailAddress
import com.github.kittinunf.statik.sample.util.load
import com.github.kittinunf.statik.sample.util.toast
import kotlinx.android.synthetic.main.activity_list.list
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var statikAdapter: StatikAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_list)

        val r1 = textRow {
            text = "One Line"
        }

        val r2 = textRow {
            text = "One Line with Icon"
            iconRes = android.R.drawable.ic_delete
        }

        val r3 = twoTextRow {
            titleText = "Two Lines"
            summaryText = "This is two-line text"
            onImageSetupListener = {
                it.load("https://ui-avatars.com/api/?name=Tanaka+Nakamura", android.R.drawable.ic_lock_idle_low_battery)
            }
        }

        val r4 = twoTextRow {
            titleText = "Two Lines with Icon"
            summaryText = "This is two-line text with icon"
            iconRes = android.R.drawable.ic_menu_call
        }

        val r5 = textRow {
            text = "You can customize the appearance"
            onTextSetupListener = {
                TextViewCompat.setTextAppearance(it, R.style.TextAppearance_AppCompat_Custom1)
            }
        }

        val r6 = textRow {
            text = "You can customize the appearance"
            onTextSetupListener = {
                TextViewCompat.setTextAppearance(it, R.style.TextAppearance_AppCompat_Custom4)
            }
        }

        val r7 = twoTextRow {
            var count = 0
            titleText = "Also, you can try to click this row"
            summaryText = "Click"
            iconRes = android.R.drawable.ic_input_add
            onClickListener = {
                summaryText = "${++count} times"
                iconRes = if (count % 2 == 0) android.R.drawable.ic_input_add
                else android.R.drawable.ic_delete

                updateList(position)
            }
        }

        val r8 = textRow {
            text = "You can observe changes, click here"
            onClickListener = {
                text = "Next random: ${Random().nextInt(10)}"
                updateList(position)
            }
            onValueChangedListener = {
                println(it.value)
            }
        }

        val h1 = textHeader {
            text = "Header"
            onTextSetupListener = {
                TextViewCompat.setTextAppearance(it, R.style.TextAppearance_AppCompat_Custom2)
            }
        }

        val f1 = textFooter {
            text = "Footer"
            onTextSetupListener = {
                it.gravity = Gravity.END
                TextViewCompat.setTextAppearance(it, R.style.TextAppearance_AppCompat_Custom3)
            }
            onClickListener = {
                println("Footer is clicked")
            }
        }

        val s1 = section {
            header(h1)
            rows(r1, r2, r3, r4, r5, r6, r7, r8)
            footer(f1)
        }

        val rs = (1..10).map { r1 }

        var savedCheckState = false

        val h2 = textHeader {
            text = "Collapsable"
            layoutRes = R.layout.widget_checkbox
            onViewSetupListener = {
                it.find<CheckBox>(android.R.id.checkbox).apply {
                    isChecked = savedCheckState
                    setOnCheckedChangeListener { _, checked ->
                        savedCheckState = checked
                        if (checked) {
                            section.rows.clear()
                        } else {
                            section.rows.addAll(rs)
                        }
                        statikAdapter.update()
                    }
                }

            }
        }

        val f2 = buttonFooter {
            text = getString(R.string.app_name)
            onButtonSetupListener = {
                it.setTextColor(Color.WHITE)
                it.setBackgroundResource(R.drawable.rounded_button_red)
            }
            onClickListener = {
                toast("Button is clicked")
            }
        }

        val s2 = section {
            header(h2)
            rows(rs)
            footer(f2)
        }

        val r9 = viewRow {
            layoutRes = R.layout.widget_two_image_view
            onViewSetupListener = {
                val image1 = it.find<ImageView>(android.R.id.icon1)
                val image2 = it.find<ImageView>(android.R.id.icon2)

                image1.load("https://source.unsplash.com/200x200/?nature", android.R.drawable.ic_menu_report_image)
                image2.load("https://source.unsplash.com/200x200/?building", android.R.drawable.ic_menu_report_image)
            }
        }

        val r10 = checkRow {
            titleText = "This is a check box row"
            summaryText = "Camera"
            iconRes = android.R.drawable.ic_menu_camera
            checked = true
            onValueChangedListener = {
                println(it.value)
            }
        }

        val r11 = switchRow {
            titleText = "This is a switch row"
            summaryText = "Camera"
            iconRes = android.R.drawable.ic_menu_camera
            checked = true
            onValueChangedListener = {
                println(it.value)
            }
        }

        val r12 = inputRow {
            hint = "Username"
            error = "Must be a valid email address"
            onValidateInput = {
                it.toString().isValidEmailAddress()
            }
        }

        val r13 = inputRow {
            hint = "Password"
            error = "Must be at least 6 characters"
            inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD or InputType.TYPE_CLASS_TEXT
            onInputLayoutSetupListener = {
                it.isPasswordVisibilityToggleEnabled = true
            }
            onValidateInput = {
                val size = it?.length ?: 0
                size >= 6
            }
            onValueChangedListener = {
                println(it.value)
            }
        }

        val r14 = spinnerRow {
            val items = listOf("Tokyo", "Bangkok", "New York", "Shanghai", "London")
            hint = "Select City"
            list = items
            selected = 2 //new york
            onSpinnerSetupListener = { spinner, underlineView ->
                underlineView.setBackgroundResource(android.R.color.darker_gray)
            }
            onItemSelectedListener = { position ->
                toast("${items[position]} is selected")
            }
        }

        val r15 = dateRow {
            text = "Tell me your birthday"
            hint = "yyyy/MM/dd"
            dateFormatter = SimpleDateFormat("yyyy/MM/dd", Locale.US)
            dialogStartingDate = Calendar.getInstance().apply { set(1984, 11, 14) }
//            year = 2000
//            month = 11
//            dayOfMonth = 14
            onTextSetupListener = {
                it.setTextColor(ContextCompat.getColor(this@MainActivity, R.color.colorPrimaryDark))
            }
            onResultTextSetupListener = {
                //you can do last-minute ad-hoc stuffs
                it.setTextColor(resources.getColor(R.color.colorAccent))
            }
            onValueChangedListener = {
                println(it.value)
            }
        }

        val s3 = section {
            rows(r9, r10, r11, r12, r13, r14, r15)
        }

        // Dynamik

        val r16 = buttonRow {
            text = "Add a Row in Section"
            onButtonSetupListener = {
                it.setTextColor(Color.WHITE)
                it.setBackgroundResource(R.drawable.rounded_button_red)
            }
            onClickListener = {
                val rowToAdd = textRow {
                    text = "Row added dynamically to section"
                }
                val sectionRef = statikAdapter.sections[3]
                sectionRef.rows.add(rowToAdd)
                statikAdapter.update()
                list.scrollToPosition(position + sectionRef.rows.size - 1)
            }
        }

        val s4 = section { rows(r16) }

        statikAdapter = statik {
            sections(s1, s2, s3, s4)
        }

        list.also {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = statikAdapter
        }
    }

    private fun updateList(at: Int) {
        list.adapter.notifyItemChanged(at)
    }
}
