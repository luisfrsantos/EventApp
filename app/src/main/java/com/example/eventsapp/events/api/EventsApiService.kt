package com.example.eventsapp.events.api

import com.example.eventsapp.events.model.Checkin
import com.example.eventsapp.events.model.CheckinResponse
import com.example.eventsapp.events.model.Events
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EventsApiService {

    @GET("events")
    suspend fun eventsList(): List<Events>

    @GET("events/{id}")
    suspend fun event(@Path("id") id: String): Events

    @POST("checkin")
    suspend fun checkin(@Body checkin: Checkin): CheckinResponse
}