package com.github.kittinunf.statik.sample

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.widget.TextViewCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import com.github.kittinunf.statik.adapter.StatikAdapter
import com.github.kittinunf.statik.dsl.section
import com.github.kittinunf.statik.dsl.statik
import com.github.kittinunf.statik.dsl.textFooter
import com.github.kittinunf.statik.dsl.textRow
import com.github.kittinunf.statik.dsl.twoTextRow
import com.github.kittinunf.statik.dsl.viewHeader
import kotlinx.android.synthetic.main.activity_user_verification.list
import kotlinx.android.synthetic.main.activity_user_verification.toolbar

class UserVerificationActivity : AppCompatActivity() {

    private lateinit var statikAdapter: StatikAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_verification)

        setSupportActionBar(toolbar)

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val spaceHeader = viewHeader {
            layoutRes = R.layout.layout_space
        }

        val mainRow = twoTextRow {
            titleText = getString(R.string.upload_documents)
            onViewSetupListener = {
                it.setBackgroundResource(android.R.color.white)
            }
            onTitleTextSetupListener = {
                TextViewCompat.setTextAppearance(it, R.style.TextAppearance_Row_Title)
            }
        }

        val descriptionRow = textRow {
            text = getString(R.string.upload_documents_detail)
            onTextSetupListener = {
                TextViewCompat.setTextAppearance(it, R.style.TextAppearance_Row_Detail)
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
            header(spaceHeader)
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
}