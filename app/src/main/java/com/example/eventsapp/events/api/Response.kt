package com.example.eventsapp.events.api

data class Response<T>(
    val code: Int = 200,
    val message: String? = null,
    var data: T? = null
)
