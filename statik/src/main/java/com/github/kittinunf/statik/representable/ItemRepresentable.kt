package com.github.kittinunf.statik.representable

import com.github.kittinunf.statik.adapter.TypeFactory

interface ItemRepresentable {

    fun type(typeFactory: TypeFactory): Int

    val stableId: Long

    var position: Int
}
