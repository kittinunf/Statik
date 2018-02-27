package com.github.kittinunf.statik.sample.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.ViewSwitcher
import com.github.kittinunf.statik.sample.R
import kotlinx.android.synthetic.main.layout_upload_area.view.descriptionText
import kotlin.properties.Delegates

class UploadAreaView @JvmOverloads constructor(context: Context,
                                               attrs: AttributeSet? = null)
    : ViewSwitcher(context, attrs) {

    var description by Delegates.observable("") { _, _, new ->
        descriptionText.text = new
    }

    val uploadImageView by lazy { findViewById<ImageView>(R.id.uploadImage) }

    init {
        View.inflate(context, R.layout.layout_upload_area, this)
    }
}

