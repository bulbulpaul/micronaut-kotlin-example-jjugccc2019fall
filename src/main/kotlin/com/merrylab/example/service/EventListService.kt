package com.merrylab.example.service

import com.merrylab.example.domain.EventSchedule

interface EventListService {
    fun eventList(): List<EventSchedule>
}