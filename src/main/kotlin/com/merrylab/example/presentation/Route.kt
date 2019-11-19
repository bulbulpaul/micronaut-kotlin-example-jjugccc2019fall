package com.merrylab.example.presentation

import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.get
import io.micronaut.ktor.KtorRoutingBuilder
import javax.inject.Singleton

@Singleton
class Route : KtorRoutingBuilder({
    get("/") {
        call.respond("Hello. JJUS CCC Fall 2019!")
    }
})