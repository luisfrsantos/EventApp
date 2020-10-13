package com.example.eventsapp.commons

enum class HTTPStatusCodes(val code: Int) {
    SUCCESS(200),
    ERROR(500),
    NOT_FOUND(402),
    BAD_REQUEST(400)
}