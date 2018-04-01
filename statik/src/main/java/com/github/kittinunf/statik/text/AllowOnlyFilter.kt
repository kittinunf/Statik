package com.github.kittinunf.statik.text

import android.text.InputFilter
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils

class AllowOnlyFilter(private val characterSet: Set<Char>) : InputFilter {

    constructor(allow: String) : this(allow.fold(hashSetOf<Char>()) { acc, each -> acc.add(each);acc })

    override fun filter(source: CharSequence?, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int): CharSequence? {
        var keepOriginal = true

        val sb = StringBuilder(end - start)

        for (i in start until end) {
            val c = source!![i]
            if (isAllowed(c)) {
                // put your condition here
                sb.append(c)
            } else {
                keepOriginal = false
            }
        }
        return if (keepOriginal) null
        else {
            if (source is Spanned) {
                val sp = SpannableString(sb)
                TextUtils.copySpansFrom(source, start, sb.length, null, sp, 0)
                sp
            } else {
                sb
            }
        }
    }

    private fun isAllowed(c: Char) = characterSet.contains(c)
}
