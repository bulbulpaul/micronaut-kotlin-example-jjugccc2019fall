package com.merrylab.example

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.micronaut.ktor.KtorApplicationBuilder
import javax.inject.Singleton

@Singleton
class Configuration : KtorApplicationBuilder({
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }
})