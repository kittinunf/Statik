package com.github.kittinunf.statik.representable

import com.github.kittinunf.statik.adapter.TypeFactory
import com.github.kittinunf.statik.model.ViewRow
import com.github.kittinunf.statik.model.ViewSupplementary

abstract class ViewSupplementaryRepresentable :
        BaseRepresentable<ViewSupplementary, Int>(ViewSupplementary()) {

    var layoutRes: Int
        set(value) {
            _value = value
        }
        get() = item.value
}

//Header
class HeaderViewSupplementaryRepresentable : ViewSupplementaryRepresentable() {

    override fun type(typeFactory: TypeFactory): Int = typeFactory.type(this)
}

//Footer
class FooterViewSupplementaryRepresentable : ViewSupplementaryRepresentable() {

    override fun type(typeFactory: TypeFactory): Int = typeFactory.type(this)
}

//Row
class ViewRowRepresentable : BaseRepresentable<ViewRow, Int>(ViewRow()) {

    var layoutRes: Int
        set(value) {
            _value = value
        }
        get() = item.value

    override fun type(typeFactory: TypeFactory): Int = typeFactory.type(this)
}