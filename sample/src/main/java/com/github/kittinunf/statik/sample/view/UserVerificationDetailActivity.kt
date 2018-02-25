package com.github.kittinunf.statik.sample.view

import android.os.Bundle
import android.support.v4.widget.TextViewCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.github.kittinunf.statik.dsl.section
import com.github.kittinunf.statik.dsl.statik
import com.github.kittinunf.statik.dsl.textHeader
import com.github.kittinunf.statik.dsl.textRow
import com.github.kittinunf.statik.sample.R
import com.github.kittinunf.statik.sample.behavior.ChildActionBarBehavior
import com.github.kittinunf.statik.sample.behavior.HomeOptionsItemSelectedBehavior
import com.github.kittinunf.statik.sample.util.configureWhiteRow
import com.github.kittinunf.statik.sample.util.toast
import kotlinx.android.synthetic.main.activity_kyc_list_template.list
import kotlinx.android.synthetic.main.activity_kyc_list_template.toolbar

class UserVerificationDetailActivity : AppCompatActivity(), ChildActionBarBehavior, HomeOptionsItemSelectedBehavior {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kyc_list_template)

        configureChildActionBar(this, toolbar)
        configureRecyclerView()
    }

    private fun configureRecyclerView() {
        val headerRow = textHeader {
            text = getString(R.string.user_verification_detail)
            onTextSetupListener = {
                TextViewCompat.setTextAppearance(it, R.style.TextAppearance_Row_Detail)
            }
        }

        val rowsItem = listOf(R.string.drivers_license,
                R.string.health_insurance_card,
                R.string.passport,
                R.string.certificate_residence)

        val detailRows = rowsItem.map {
            textRow {
                val string = getString(it)
                text = string
                onViewSetupListener = configureWhiteRow()
                onTextSetupListener = {
                    TextViewCompat.setTextAppearance(it, R.style.TextAppearance_Row_Title)
                }
                onClickListener = { _, _ ->
                    toast(string)
                }
            }
        }

        val section = section {
            header(headerRow)
            rows(detailRows)
        }

        val adapter = statik {
            sections(section)
        }

        list.also {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = adapter
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
            onHomeOptionItemSelected(this, item) ?: super.onOptionsItemSelected(item)
}