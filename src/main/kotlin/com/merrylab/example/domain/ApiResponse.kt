package com.merrylab.example.domain

enum class Status {
    SUCESS,
    ERROR
}

data class ApiRespoonse(val status: Status, val data: Any)