package com.github.kittinunf.statik.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.kittinunf.statik.dsl.statik
import kotlinx.android.synthetic.main.activity_list.statikView

class SampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_list)

        statikView.adapter = statik {}
    }
}