package com.github.kittinunf.statik.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.kittinunf.statik.dsl.adapter
import kotlinx.android.synthetic.main.activity_list.list

class SampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_list)

        list.adapter = adapter {}
    }
}
