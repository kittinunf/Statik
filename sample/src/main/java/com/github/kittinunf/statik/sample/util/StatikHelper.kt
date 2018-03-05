package com.github.kittinunf.statik.sample.util

import android.support.v4.widget.TextViewCompat
import android.view.View
import android.widget.TextView
import com.github.kittinunf.statik.sample.R

fun configureWhiteRow() = { view: View ->
    view.setBackgroundResource(android.R.color.white)
}

fun configureTitleText() = { textView: TextView ->
    TextViewCompat.setTextAppearance(textView, R.style.TextAppearance_Row_Title)
}

fun configureDetailText() = { textView: TextView ->
    TextViewCompat.setTextAppearance(textView, R.style.TextAppearance_Row_Detail)
}
