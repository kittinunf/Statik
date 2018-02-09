package com.github.kittinunf.statik.sample.util

import android.content.Context
import android.support.annotation.IdRes
import android.view.View
import android.widget.Toast

fun <T : View> View.find(@IdRes id: Int): T = findViewById(id)

fun Context.toast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}
