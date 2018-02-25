package com.github.kittinunf.statik.sample.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.widget.TextViewCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import com.github.kittinunf.statik.dsl.section
import com.github.kittinunf.statik.dsl.statik
import com.github.kittinunf.statik.dsl.textFooter
import com.github.kittinunf.statik.dsl.textHeader
import com.github.kittinunf.statik.dsl.viewRow
import com.github.kittinunf.statik.sample.R
import com.github.kittinunf.statik.sample.behavior.ChildActionBarBehavior
import com.github.kittinunf.statik.sample.behavior.HomeAsBackPressedOptionsItemSelectedBehavior
import com.github.kittinunf.statik.sample.util.configureDetailText
import com.github.kittinunf.statik.sample.util.find
import com.github.kittinunf.statik.sample.util.toast
import kotlinx.android.synthetic.main.activity_kyc_list_template.list
import kotlinx.android.synthetic.main.activity_kyc_list_template.toolbar
import kotlinx.android.synthetic.main.layout_upload_button.view.nextButton

class UploadDocumentActivity : AppCompatActivity(),
        ChildActionBarBehavior,
        HomeAsBackPressedOptionsItemSelectedBehavior {

    private val hasSubmittedDocument = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kyc_list_template)

        configureChildActionBar(this, toolbar)
        configureRecyclerView()
    }

    private fun configureRecyclerView() {
        val headerRow = textHeader {
            text = getString(R.string.submit_verification_document)
            onTextSetupListener = configureDetailText()
        }

        val uploadDocumentRow = viewRow {
            layoutRes = R.layout.layout_upload_document
            onViewSetupListener = {

            }
        }

        val buttonRow = viewRow {
            layoutRes = R.layout.layout_upload_button
            onViewSetupListener = {
                it.nextButton.isEnabled = hasSubmittedDocument
                if (hasSubmittedDocument) {
                    it.find<View>(R.id.agreeButton).setOnClickListener {
                        toast("Yey!")
                    }
                }
            }
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
            header(headerRow)
            rows(uploadDocumentRow, buttonRow)
            footer(aboutFooter)
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