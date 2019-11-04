package com.merrylab.example

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("com.merrylab.example")
                .mainClass(Application.javaClass)
                .start()
    }
}