package com.example.eventsapp.events.model

data class Events(
    val id: String,
    val title: String,
    val people: List<People>,
    val date: Long,
    val description: String,
    val image: String,
    val longitude: Float,
    val latitude: Float,
    val price: Double
)