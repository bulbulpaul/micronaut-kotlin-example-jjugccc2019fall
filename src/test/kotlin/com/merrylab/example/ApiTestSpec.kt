package com.merrylab.example

import io.micronaut.context.ApplicationContext
import io.micronaut.http.client.HttpClient
import io.micronaut.runtime.server.EmbeddedServer
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals

object ApiTestSpec : Spek({

    describe("API サーバーのテストケース") {
        val server = ApplicationContext.run(EmbeddedServer::class.java)
        val client: HttpClient = HttpClient.create(server.url)

        it("/のリクエストで　Hello. JJUG CCC Fall 2019! が返ってくること") {
            val rsp: String = client.toBlocking().retrieve("/")
            assertEquals("Hello. JJUG CCC Fall 2019!", rsp)
        }

        afterGroup {
            client.close()
            server.close()
        }
    }
})