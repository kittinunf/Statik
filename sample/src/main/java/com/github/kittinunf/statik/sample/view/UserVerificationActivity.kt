package com.github.kittinunf.statik.sample.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.widget.TextViewCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.MenuItem
import com.github.kittinunf.statik.adapter.StatikAdapter
import com.github.kittinunf.statik.dsl.section
import com.github.kittinunf.statik.dsl.statik
import com.github.kittinunf.statik.dsl.textFooter
import com.github.kittinunf.statik.dsl.textRow
import com.github.kittinunf.statik.dsl.twoTextRow
import com.github.kittinunf.statik.sample.R
import com.github.kittinunf.statik.sample.behavior.ChildActionBarBehavior
import com.github.kittinunf.statik.sample.behavior.HomeAsBackPressedOptionsItemSelectedBehavior
import com.github.kittinunf.statik.sample.util.configureDetailText
import com.github.kittinunf.statik.sample.util.configureTitleText
import com.github.kittinunf.statik.sample.util.configureWhiteRow
import com.github.kittinunf.statik.sample.util.toast
import kotlinx.android.synthetic.main.activity_kyc_list_template.list
import kotlinx.android.synthetic.main.activity_kyc_list_template.toolbar

class UserVerificationActivity : AppCompatActivity(),
        ChildActionBarBehavior,
        HomeAsBackPressedOptionsItemSelectedBehavior {

    private lateinit var statikAdapter: StatikAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kyc_list_template)

        configureChildActionBar(this, toolbar)
        configureRecyclerView()
    }

    private fun configureRecyclerView() {
        val mainRow = twoTextRow {
            titleText = getString(R.string.upload_documents)
            onViewSetupListener = configureWhiteRow()
            onTitleTextSetupListener = configureTitleText()
            onClickListener = { _, _ ->
                toast("Yey!")
            }
        }

        val descriptionRow = textRow {
            text = getString(R.string.upload_documents_detail)
            onTextSetupListener = configureDetailText()
        }

        val aboutFooter = textFooter {
            text = getString(R.string.about_upload_documents)
            onTextSetupListener = {
                it.gravity = Gravity.END
                TextViewCompat.setTextAppearance(it, R.style.TextAppearance_Row_About)
            }
            onClickListener = { _, _ ->
                val aboutPageIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/"))
                startActivity(aboutPageIntent)
            }
        }

        val section = section {
            rows(mainRow, descriptionRow)
            footer(aboutFooter)
        }

        statikAdapter = statik {
            sections(section)
        }

        list.also {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = statikAdapter
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
            onHomeOptionItemSelected(this, item) ?: super.onOptionsItemSelected(item)
}