package com.github.kittinunf.statik.model

import android.support.annotation.LayoutRes

sealed class SupplementaryOf<T>(var value: T)

class TextSupplementary(text: String = "", @LayoutRes widgetRes: Int? = null) :
        SupplementaryOf<Pair<String, Int?>>(text to widgetRes)

class ViewSupplementary(@LayoutRes layoutRes: Int = 0) : SupplementaryOf<Int>(layoutRes)