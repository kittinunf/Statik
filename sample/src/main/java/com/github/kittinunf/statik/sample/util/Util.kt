package com.github.kittinunf.statik.sample.util

import android.content.Context
import android.support.annotation.IdRes
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso

fun <T : View> View.find(@IdRes id: Int): T = findViewById(id)

fun Context.toast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun ImageView.load(url: String) {
    Picasso.with(context)
            .load(url)
            .fit()
            .centerCrop()
            .into(this)
}

fun String.isValidEmailAddress(): Boolean {
    val regex = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$".toRegex()
    return regex.matches(this)
}
