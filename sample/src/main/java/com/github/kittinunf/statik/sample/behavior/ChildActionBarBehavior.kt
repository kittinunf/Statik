package com.github.kittinunf.statik.sample.behavior

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

interface ChildActionBarBehavior {

    fun configureChildActionBar(activity: AppCompatActivity, toolbar: Toolbar) {
        activity.apply {
            setSupportActionBar(toolbar)
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
            }
        }
    }
}