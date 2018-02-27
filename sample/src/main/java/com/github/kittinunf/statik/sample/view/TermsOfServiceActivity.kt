package com.github.kittinunf.statik.sample.view

import android.os.Bundle
import android.support.v4.widget.NestedScrollView
import android.support.v4.widget.TextViewCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.github.kittinunf.statik.adapter.StatikAdapter
import com.github.kittinunf.statik.dsl.section
import com.github.kittinunf.statik.dsl.statik
import com.github.kittinunf.statik.dsl.textFooter
import com.github.kittinunf.statik.dsl.textHeader
import com.github.kittinunf.statik.dsl.viewFooter
import com.github.kittinunf.statik.dsl.viewRow
import com.github.kittinunf.statik.sample.R
import com.github.kittinunf.statik.sample.behavior.ChildActionBarBehavior
import com.github.kittinunf.statik.sample.behavior.HomeAsBackPressedOptionsItemSelectedBehavior
import com.github.kittinunf.statik.sample.util.configureDetailText
import com.github.kittinunf.statik.sample.util.toast
import kotlinx.android.synthetic.main.activity_kyc_list_template.list
import kotlinx.android.synthetic.main.activity_kyc_list_template.toolbar
import kotlinx.android.synthetic.main.layout_scrollable_text.view.tosContent
import kotlinx.android.synthetic.main.layout_next_button.view.agreeButton

class TermsOfServiceActivity : AppCompatActivity(),
        ChildActionBarBehavior,
        HomeAsBackPressedOptionsItemSelectedBehavior {

    private lateinit var adapter: StatikAdapter

    private var hasReachBottom = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kyc_list_template)

        configureChildActionBar(this, toolbar)
        configureRecyclerView()
    }

    private fun configureRecyclerView() {
        val infoRow = textHeader {
            text = getString(R.string.account_terms_of_service)
            onTextSetupListener = configureDetailText()
        }

        val contentRow = viewRow {
            layoutRes = R.layout.layout_scrollable_text
            onViewSetupListener = { view ->
                view.apply {
                    tosContent.setOnTouchListener { v, _ ->
                        v.parent.requestDisallowInterceptTouchEvent(true)
                        false
                    }
                    tosContent.setOnScrollChangeListener { scrollView: NestedScrollView, _: Int, scrollY: Int, _: Int, _: Int ->
                        if (scrollY >= scrollView.height) {
                            hasReachBottom = true
                            adapter.update()
                        }
                    }
                }
            }
        }

        val buttonRow = viewFooter {
            layoutRes = R.layout.layout_next_button
            onViewSetupListener = {
                it.agreeButton.isEnabled = hasReachBottom
                if (hasReachBottom) {
                    it.agreeButton.setOnClickListener {
                        toast("Yey!")
                    }
                }
            }
        }

        val warningRow = textFooter {
            text = getString(R.string.warning_terms_of_service)
            onTextSetupListener = {
                TextViewCompat.setTextAppearance(it, R.style.TextAppearance_Row_About)
            }
        }

        val section = section {
            header(infoRow)
            rows(contentRow, warningRow)
            footer(buttonRow)
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