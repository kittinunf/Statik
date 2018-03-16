package com.github.kittinunf.statik.sample.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.github.kittinunf.statik.adapter.StatikAdapter
import com.github.kittinunf.statik.dsl.section
import com.github.kittinunf.statik.dsl.statik
import com.github.kittinunf.statik.dsl.textHeader
import com.github.kittinunf.statik.dsl.textRow
import com.github.kittinunf.statik.dsl.viewFooter
import com.github.kittinunf.statik.dsl.viewRow
import com.github.kittinunf.statik.sample.R
import com.github.kittinunf.statik.sample.behavior.ChildActionBarBehavior
import com.github.kittinunf.statik.sample.behavior.HomeAsBackPressedOptionsItemSelectedBehavior
import com.github.kittinunf.statik.sample.util.configureDetailText
import com.github.kittinunf.statik.sample.util.configureTitleText
import com.github.kittinunf.statik.sample.util.load
import com.github.kittinunf.statik.sample.util.toast
import kotlinx.android.synthetic.main.activity_kyc_list_template.list
import kotlinx.android.synthetic.main.activity_kyc_list_template.toolbar
import kotlinx.android.synthetic.main.layout_next_skip_button.view.nextButton
import kotlinx.android.synthetic.main.layout_next_skip_button.view.skipButton
import kotlinx.android.synthetic.main.layout_upload_additional_document.view.container

class UploadAdditionalDocumentActivity : AppCompatActivity(), ChildActionBarBehavior, HomeAsBackPressedOptionsItemSelectedBehavior {

    private lateinit var adapter: StatikAdapter

    private var hasImageUploaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kyc_list_template)

        configureChildActionBar(this, toolbar)
        configureRecyclerView()
    }

    private fun configureRecyclerView() {
        val headerRow = textHeader {
            text = getString(R.string.current_address_doesnt_match)
            onTextSetupListener = configureTitleText()
        }

        val detail1Row = textRow {
            text = getString(R.string.additional_document_detail1)
            onTextSetupListener = configureDetailText()
        }

        val detail2Row = textRow {
            text = getString(R.string.additional_document_detail2)
            onTextSetupListener = configureDetailText()
        }

        val uploadRow = viewRow {
            layoutRes = R.layout.layout_upload_additional_document
            onViewSetupListener = { view ->
                view.container.apply {
                    description = getString(R.string.submit_additional_document)
                    setOnClickListener {
                        uploadImageView.load("https://source.unsplash.com/200x200/?nature")
                        showNext()
                        hasImageUploaded = true
                        adapter.update()
                    }
                }
            }
        }

        val actionRow = viewFooter {
            layoutRes = R.layout.layout_next_skip_button
            onViewSetupListener = { view ->
                view.nextButton.setOnClickListener {
                    toast("Yey!")
                }
                view.nextButton.isEnabled = hasImageUploaded

                view.skipButton.setOnClickListener {
                    toast("Neh")
                }
            }
        }

        val section = section {
            header(headerRow)
            rows(detail1Row, detail2Row, uploadRow)
            footer(actionRow)
        }

        adapter = statik {
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