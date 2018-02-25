package com.github.kittinunf.statik.sample.behavior

import android.support.v7.app.AppCompatActivity
import android.view.MenuItem

interface HomeOptionsItemSelectedBehavior {

    fun onHomeOptionItemSelected(activity: AppCompatActivity, item: MenuItem): Boolean? {
        when (item.itemId) {
            android.R.id.home -> {
                activity.onBackPressed()
                return true
            }
        }
        return null
    }
}