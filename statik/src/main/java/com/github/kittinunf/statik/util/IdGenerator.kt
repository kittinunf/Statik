package com.github.kittinunf.statik.util

import java.util.concurrent.atomic.AtomicLong

object IdGenerator {
    private val generator = AtomicLong()

    fun generate(): Long {
        return generator.getAndIncrement()
    }
}
