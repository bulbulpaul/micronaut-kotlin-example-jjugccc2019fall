package com.merrylab.example

import io.ktor.server.netty.NettyApplicationEngine
import io.micronaut.ktor.KtorApplication
import io.micronaut.ktor.runApplication
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class Application : KtorApplication<NettyApplicationEngine.Configuration>({
    applicationEngineEnvironment {
        log = LoggerFactory.getLogger(Application::class.java)
    }

    applicationEngine {
        workerGroupSize = 20
    }
})

fun main(args: Array<String>) {
    runApplication(args)
}