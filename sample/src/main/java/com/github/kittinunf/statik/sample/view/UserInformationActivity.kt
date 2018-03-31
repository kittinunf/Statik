package com.github.kittinunf.statik.sample.view

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.InputType
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import com.github.kittinunf.statik.adapter.StatikAdapter
import com.github.kittinunf.statik.dsl.buttonFooter
import com.github.kittinunf.statik.dsl.buttonHeader
import com.github.kittinunf.statik.dsl.dateRow
import com.github.kittinunf.statik.dsl.inputRow
import com.github.kittinunf.statik.dsl.section
import com.github.kittinunf.statik.dsl.spinnerRow
import com.github.kittinunf.statik.dsl.statik
import com.github.kittinunf.statik.dsl.viewHeader
import com.github.kittinunf.statik.sample.R
import com.github.kittinunf.statik.sample.behavior.ChildActionBarBehavior
import com.github.kittinunf.statik.sample.behavior.HomeAsBackPressedOptionsItemSelectedBehavior
import java.text.SimpleDateFormat
import java.util.*

open class UserInformationActivity : AppCompatActivity(), ChildActionBarBehavior, HomeAsBackPressedOptionsItemSelectedBehavior {

    enum class SectionOrder {

        PERSONAL,
        ADDRESS,
        OCCUPATION;
    }

    enum class Gender {

        MALE,
        FEMALE
    }

    enum class Occupation {

        EMPLOYEE,
        EXECUTIVE,
        PUBLIC_EMPLOYEE,
        SELF_EMPLOYED,
        CONTRACT_EMPLOYEE,
        PART_TIMER,
        STAY_AT_HOME,
        PENSIONER,
        STUDENT,
        NOT_IN_EMPLOYMENT,
        OTHERS
    }

    protected lateinit var adapter: StatikAdapter
        private set

    protected var editable = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kyc_list_template)

        if (!editable) window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        configureChildActionBar(this, findViewById(R.id.toolbar))
        configureRecyclerView()
    }

    private fun configureRecyclerView() {
        val personalHeader = viewHeader {
            layoutRes = R.layout.layout_section_header
            onViewSetupListener = {
                val sectionTitle = it.findViewById<TextView>(R.id.sectionTitle)
                val sectionSubtitle = it.findViewById<View>(R.id.sectionSubtitle)
                sectionTitle.text = getString(R.string.customer_information)
                sectionSubtitle.visibility = View.GONE
            }
        }

        val personalRows = listOf(R.string.last_name_full,
                R.string.first_name_full,
                R.string.last_name_kana_full,
                R.string.first_name_kana_full)
                .map {
                    inputRow {
                        hint = getString(it)
                        error = String.format(getString(R.string.error_please_enter), getString(it))
                        onValidateInput = { !it.isNullOrEmpty() }
                        onValueChangedListener = { inputRow ->
                            println(inputRow.value)
                        }
                        allowCharacters = "abcABC"
                    }
                }

        val genderRow = spinnerRow {
            hint = getString(R.string.gender)
            list = Gender.values().map { it.name }
            onSpinnerSetupListener = { spinner, view ->
                spinner.isEnabled = editable
                spinner.isClickable = editable
                view.setBackgroundResource(R.color.charcoal_gray)
            }
            onValueChangedListener = { spinnerRow ->
            }
        }

        val birthdayRow = dateRow {
            text = "Birthday"
            hint = "yyyy-mm-dd"
            dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            onTextSetupListener = {
                it.setTextColor(ContextCompat.getColor(this@UserInformationActivity, R.color.choco_black))
            }
            onResultTextSetupListener = {
                it.isEnabled = editable
            }
            onDateSelectedListener = { _, year, month, dayOfMonth ->
            }
        }

        val personalSection = section {
            header(personalHeader)
            rows(personalRows + genderRow + birthdayRow)
        }

        val addressHeader = viewHeader {
            layoutRes = R.layout.layout_section_header
            onViewSetupListener = {
                val sectionTitle = it.findViewById<TextView>(R.id.sectionTitle)
                val sectionSubtitle = it.findViewById<TextView>(R.id.sectionSubtitle)
                sectionTitle.text = getString(R.string.customer_address)
                sectionSubtitle.text = getString(R.string.customer_address_detail)
            }
        }

        val addressRows = listOf(
                R.string.postal_code to InputType.TYPE_CLASS_NUMBER,
                R.string.prefecture to InputType.TYPE_CLASS_TEXT,
                R.string.city to InputType.TYPE_CLASS_TEXT,
                R.string.street_number to InputType.TYPE_CLASS_TEXT,
                R.string.building_name to InputType.TYPE_CLASS_TEXT,
                R.string.phone_number to InputType.TYPE_CLASS_PHONE)
                .map { (stringRes, type) ->
                    inputRow {
                        hint = getString(stringRes)
                        inputType = type
                        if (stringRes == R.string.postal_code) {
                            error = getString(R.string.error_zip_code)
                            onValidateInput = {
                                val length = it?.length ?: 0
                                length == 7
                            }
                        } else if (stringRes != R.string.building_name) {
                            error = String.format(getString(R.string.error_please_enter), getString(stringRes))
                            onValidateInput = { !it.isNullOrEmpty() }
                        }

                        onValueChangedListener = { inputRow ->
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
                val sectionTitle = it.findViewById<TextView>(R.id.sectionTitle)
                val sectionSubtitle = it.findViewById<View>(R.id.sectionSubtitle)
                sectionTitle.text = getString(R.string.transaction_confirmation)
                sectionSubtitle.visibility = View.GONE
            }
        }

        val occupationRow = spinnerRow {
            hint = getString(R.string.occupation)
            list = Occupation.values().map { it.name }
            onSpinnerSetupListener = { spinner, view ->
                spinner.isEnabled = editable
                spinner.isClickable = editable
                view.setBackgroundResource(R.color.charcoal_gray)
            }
            onValueChangedListener = { spinnerRow ->
            }
        }

        val transactionSection = section {
            header(transactionHeader)
            rows(occupationRow)
        }

        val registerHeader = buttonHeader {
            text = getString(R.string.register)
            onClickListener = { _, _ ->
            }
        }

        val changeInfoFooter = buttonFooter {
            text = getString(R.string.change_user_information)
            onClickListener = { _, _ ->
            }
        }

        val actionSection = section {
            header(registerHeader)
            if (!editable) footer(changeInfoFooter)
        }

        adapter = statik {
            sections(personalSection, addressSection, transactionSection, actionSection)
        }

        findViewById<View>(R.id.layoutContainer).setBackgroundResource(android.R.color.white)
        findViewById<RecyclerView>(R.id.list).also {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = adapter
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
            onHomeOptionItemSelected(this, item) ?: super.onOptionsItemSelected(item)
}
