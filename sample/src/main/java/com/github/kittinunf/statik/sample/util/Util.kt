package com.github.kittinunf.statik.sample.util

import android.content.Context
import android.content.Intent
import android.support.annotation.IdRes
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlin.reflect.KClass

fun <T : View> View.find(@IdRes id: Int): T = findViewById(id)

fun <T : View> AppCompatActivity.find(@IdRes id: Int): T = findViewById(id)

fun Context.toast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

inline fun <reified T : AppCompatActivity> Context.navigate() {
    navigate(T::class)
}

fun <T : Any> Context.navigate(clazz: KClass<T>) {
    val intent = Intent(this, clazz.java)
    startActivity(intent)
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

fun String.resString(context: Context): Int {
    val resource = context.resources
    return resource.getIdentifier(this.toLowerCase(), "string", context.packageName)
}
