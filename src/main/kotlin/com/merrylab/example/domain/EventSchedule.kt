package com.merrylab.example.domain

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

data class EventSchedule(
    val title: String,
    @JsonProperty("start_date") val startDate: LocalDate,
    val location: String
)