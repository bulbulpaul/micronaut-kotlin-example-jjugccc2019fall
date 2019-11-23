package com.merrylab.example.presentation

import com.merrylab.example.domain.ApiRespoonse
import com.merrylab.example.domain.Status
import com.merrylab.example.service.EventListService
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.get
import io.micronaut.ktor.KtorRoutingBuilder
import javax.inject.Singleton

@Singleton
class Route(private val eventListService: EventListService) : KtorRoutingBuilder(
    {
    get("/") {
        call.respond("Hello. JJUG CCC Fall 2019!")
    }

    get("/eventlist") {
        val eventList = eventListService.eventList()
        call.respond(ApiRespoonse(Status.SUCESS, eventList))
    }
})