package com.github.kittinunf.statik.sample

import android.os.Bundle
import android.support.v4.widget.TextViewCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.TextView
import com.github.kittinunf.statik.dsl.statik
import com.github.kittinunf.statik.dsl.textRow
import com.github.kittinunf.statik.sample.util.configureWhiteRow
import com.github.kittinunf.statik.sample.util.navigate
import kotlinx.android.synthetic.main.activity_list.list
import kotlinx.android.synthetic.main.activity_list.toolbar

class SampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_list)

        setSupportActionBar(toolbar)

        val userVerificationRow = textRow {
            text = getString(R.string.user_verification)
            onViewSetupListener = configureWhiteRow()
            onTextSetupListener = configureTitleText()
            onClickListener = { _, _ ->
                navigate<UserVerificationActivity>()
            }
        }

        val tosRow = textRow {
            text = getString(R.string.terms_of_service)
            onViewSetupListener = configureWhiteRow()
            onTextSetupListener = configureTitleText()
            onClickListener = { _, _ ->
                navigate<TermsOfServiceActivity>()
            }
        }

        val adapter = statik {
            section {
                rows(userVerificationRow, tosRow)
            }
        }

        list.also {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = adapter
        }
    }

    fun configureTitleText() = { textView: TextView ->
        TextViewCompat.setTextAppearance(textView, R.style.TextAppearance_Row_Title)
    }
}