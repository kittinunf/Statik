package com.github.kittinunf.statik.sample.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.InputType
import android.view.MenuItem
import android.view.View
import com.github.kittinunf.statik.adapter.StatikAdapter
import com.github.kittinunf.statik.dsl.inputRow
import com.github.kittinunf.statik.dsl.section
import com.github.kittinunf.statik.dsl.spinnerRow
import com.github.kittinunf.statik.dsl.statik
import com.github.kittinunf.statik.dsl.viewFooter
import com.github.kittinunf.statik.dsl.viewHeader
import com.github.kittinunf.statik.sample.R
import com.github.kittinunf.statik.sample.behavior.ChildActionBarBehavior
import com.github.kittinunf.statik.sample.behavior.HomeAsBackPressedOptionsItemSelectedBehavior
import com.github.kittinunf.statik.sample.util.resString
import com.github.kittinunf.statik.sample.util.toast
import kotlinx.android.synthetic.main.activity_kyc_list_template.layoutContainer
import kotlinx.android.synthetic.main.activity_kyc_list_template.list
import kotlinx.android.synthetic.main.activity_kyc_list_template.toolbar
import kotlinx.android.synthetic.main.layout_next_button.view.agreeButton
import kotlinx.android.synthetic.main.layout_section_header.view.sectionSubtitle
import kotlinx.android.synthetic.main.layout_section_header.view.sectionTitle

class UserInformationActivity : AppCompatActivity(), ChildActionBarBehavior, HomeAsBackPressedOptionsItemSelectedBehavior {

    enum class Occupation {
        NOT_SELECTED,
        EMPLOYEE,
        EXECUTIVE,
        PUBLIC_EMPLOYEE,
        SELF_EMPLOYED,
        CONTRACT_EMPLOYEE,
        PART_TIMER,
        PENSIONER,
        STUDENT,
        NOT_IN_EMPLOYMENT,
        OTHERS;
    }

    private lateinit var adapter: StatikAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kyc_list_template)

        configureChildActionBar(this, toolbar)
        configureRecyclerView()
    }

    private fun configureRecyclerView() {
        val personalHeader = viewHeader {
            layoutRes = R.layout.layout_section_header
            onViewSetupListener = {
                it.sectionTitle.text = getString(R.string.customer_information)
                it.sectionSubtitle.visibility = View.GONE
            }
        }

        val personalRows = listOf(R.string.last_name_full,
                R.string.first_name_full,
                R.string.last_name_kana_full,
                R.string.first_name_kana_full)
                .map { getString(it) }
                .map {
                    inputRow {
                        hint = it
                        onValueChangedListener = {
                            println(it.value)
                        }
                    }
                }

        val genderRow = spinnerRow {
            list = listOf("Male", "Female")
        }

        val personalSection = section {
            header(personalHeader)
            val allRows = personalRows + genderRow
            rows(allRows)
        }

        val addressHeader = viewHeader {
            layoutRes = R.layout.layout_section_header
            onViewSetupListener = {
                it.sectionTitle.text = getString(R.string.customer_address)
                it.sectionSubtitle.text = getString(R.string.customer_address_detail)
            }
        }

        val addressRows = listOf(
                R.string.postal_code to InputType.TYPE_CLASS_NUMBER,
                R.string.prefecture to InputType.TYPE_CLASS_TEXT,
                R.string.city to InputType.TYPE_CLASS_TEXT,
                R.string.street_number to InputType.TYPE_CLASS_NUMBER,
                R.string.building_name to InputType.TYPE_CLASS_TEXT,
                R.string.phone_number to InputType.TYPE_CLASS_PHONE)
                .map { (stringRes, inputType) -> getString(stringRes) to inputType }
                .map { (label, type) ->
                    inputRow {
                        hint = label
                        inputType = type
                        onValueChangedListener = {
                            println(it.value)
                        }
                    }
                }

        val addressSection = section {
            header(addressHeader)
            rows(addressRows)
        }

        val transactionHeader = viewHeader {
            layoutRes = R.layout.layout_section_header
            onViewSetupListener = {
                it.sectionTitle.text = getString(R.string.transaction_confirmation)
                it.sectionSubtitle.visibility = View.GONE
            }
        }

        val occupationRow = spinnerRow {
            hint = "OCCUPATION"
            list = Occupation.values().map { it.name.resString(this@UserInformationActivity) }
                    .map(::getString)
            onValueChangedListener = {
                println(it.value)
            }
        }

        val buttonFooter = viewFooter {
            layoutRes = R.layout.layout_next_button
            onViewSetupListener = {
                it.agreeButton.setOnClickListener {
                    toast("Yey!")
                }
            }
        }

        val transactionSection = section {
            header(transactionHeader)
            rows(occupationRow)
            footer(buttonFooter)
        }

        adapter = statik {
            sections(personalSection, addressSection, transactionSection)
        }

        layoutContainer.setBackgroundResource(android.R.color.white)
        list.also {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = adapter
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
            onHomeOptionItemSelected(this, item) ?: super.onOptionsItemSelected(item)
}