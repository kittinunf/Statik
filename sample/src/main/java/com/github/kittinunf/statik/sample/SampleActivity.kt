package com.github.kittinunf.statik.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.github.kittinunf.statik.dsl.statik
import com.github.kittinunf.statik.dsl.textRow
import com.github.kittinunf.statik.sample.util.configureTitleText
import com.github.kittinunf.statik.sample.util.configureWhiteRow
import com.github.kittinunf.statik.sample.util.navigate
import com.github.kittinunf.statik.sample.view.UploadAdditionalDocumentActivity
import com.github.kittinunf.statik.sample.view.TermsOfServiceActivity
import com.github.kittinunf.statik.sample.view.UploadDocumentActivity
import com.github.kittinunf.statik.sample.view.UserInformationActivity
import com.github.kittinunf.statik.sample.view.UserVerificationActivity
import com.github.kittinunf.statik.sample.view.UserVerificationDetailActivity
import kotlinx.android.synthetic.main.activity_list.list
import kotlinx.android.synthetic.main.activity_list.toolbar

class SampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_list)

        setSupportActionBar(toolbar)

        val labelAndActivity = mapOf(
                R.string.user_verification to UserVerificationActivity::class,
                R.string.terms_of_service to TermsOfServiceActivity::class,
                R.string.user_verification2 to UserVerificationDetailActivity::class,
                R.string.user_verification_document to UploadDocumentActivity::class,
                R.string.submit_additional_document to UploadAdditionalDocumentActivity::class,
                R.string.user_information to UserInformationActivity::class
                )

        val rows = labelAndActivity.map { (label, clazz) ->
            textRow {
                text = getString(label)
                onViewSetupListener = configureWhiteRow()
                onTextSetupListener = configureTitleText()
                onClickListener = { _, _ -> navigate(clazz) }
            }
        }

        val adapter = statik {
            section {
                rows(rows)
            }
        }

        list.also {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = adapter
        }
    }
}