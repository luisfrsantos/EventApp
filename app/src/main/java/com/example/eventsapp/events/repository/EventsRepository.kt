package com.example.eventsapp.events.repository

import com.example.eventsapp.commons.HTTPStatusCodes
import com.example.eventsapp.events.api.EventsApiService
import com.example.eventsapp.events.api.Response
import com.example.eventsapp.events.model.Checkin
import com.example.eventsapp.events.model.CheckinResponse
import com.example.eventsapp.events.model.Events
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

class EventsRepository @Inject constructor(var eventsApiService: EventsApiService) {

    suspend fun eventList(): Response<List<Events>> = try {
        val data = eventsApiService.eventsList()
        if (data.isEmpty()) {
            Response(code = HTTPStatusCodes.BAD_REQUEST.code)
        } else {
            Response(data = data)
        }
    } catch (e: HttpException) {
        Response(code = e.code(), message = e.message())
    } catch (e: UnknownHostException) {
        Response(code = HTTPStatusCodes.ERROR.code, message = "Connection error")
    }

    suspend fun event(id: String): Response<Events> = try {
        val data = eventsApiService.event(id)
        Response(data = data)
    } catch (e: HttpException) {
        Response(code = e.code(), message = e.message())
    }

    suspend fun checkin(checkin: Checkin): Response<CheckinResponse> = try {
        val data = eventsApiService.checkin(checkin)
        Response(data = data)
    } catch (e: HttpException) {
        Response(code = e.code(), message = e.message())
    }
}