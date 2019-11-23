package com.merrylab.example.service.impl

import com.merrylab.example.domain.EventSchedule
import com.merrylab.example.service.EventListService
import java.time.LocalDate
import javax.inject.Singleton

@Singleton
class ConnpassEventService : EventListService {

    private val demoResponses = listOf(
            EventSchedule("JJUG CCC 2019 Fall", LocalDate.of(2019, 11, 23), "ベルサール新宿グランド"),
            EventSchedule("Kotlin Conf 2019", LocalDate.of(2019, 12, 4), "Copenhagen"))

    override fun eventList(): List<EventSchedule> {
        return demoResponses
    }
}