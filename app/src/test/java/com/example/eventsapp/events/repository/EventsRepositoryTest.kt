package com.example.eventsapp.events.repository

import com.example.eventsapp.commons.HTTPStatusCodes
import com.example.eventsapp.commons.helper.JsonHelper.readJsonFile
import com.example.eventsapp.events.api.EventsApiService
import com.example.eventsapp.events.model.Checkin
import com.example.eventsapp.events.model.CheckinResponse
import com.example.eventsapp.events.model.Events
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.lang.reflect.Type

class EventsRepositoryTest {
    private val eventsApiService: EventsApiService = mockk()
    private val listType: Type = object : TypeToken<List<Events>>() {}.type

    @Test
    fun eventListSuccess() {
        val mockResponse: List<Events> = Gson().fromJson(readJsonFile("events.json"), listType)
        every {
            runBlocking {
                eventsApiService.eventsList()
            }
        } returns mockResponse

        val eventsRepository = EventsRepository(eventsApiService)
        runBlocking {
            val response = eventsRepository.eventList()
            assert(response.code == HTTPStatusCodes.SUCCESS.code)
            assert(response.data?.isNotEmpty()!!)
        }
    }

    @Test
    fun eventListNoData() {
        val mockResponse: List<Events> = arrayListOf()
        every {
            runBlocking {
                eventsApiService.eventsList()
            }
        } returns mockResponse

        val eventsRepository = EventsRepository(eventsApiService)
        runBlocking {
            val response = eventsRepository.eventList()
            assert(response.code == HTTPStatusCodes.BAD_REQUEST.code)
            assert(response.data.isNullOrEmpty())
        }
    }

    @Test
    fun eventListHttpException() {
        val mockResponse = Response.error<List<Events>>(HTTPStatusCodes.ERROR.code, mockk())
        every {
            runBlocking {
                eventsApiService.eventsList()
            }
        } throws HttpException(mockResponse)

        val eventsRepository = EventsRepository(eventsApiService)
        runBlocking {
            val response = eventsRepository.eventList()
            assert(response.code == HTTPStatusCodes.ERROR.code)
            assert(response.data.isNullOrEmpty())
        }
    }

    @Test
    fun eventSuccess() {
        val mockResponse: Events = Gson().fromJson(readJsonFile("event.json"), Events::class.java)
        every {
            runBlocking {
                eventsApiService.event(EVENT_ID)
            }
        } returns mockResponse

        val eventsRepository = EventsRepository(eventsApiService)
        runBlocking {
            val response = eventsRepository.event(EVENT_ID)
            assert(response.code == HTTPStatusCodes.SUCCESS.code)
            assert(response.data != null)
        }
    }

    @Test
    fun eventHttpException() {
        val mockResponse = Response.error<List<Events>>(HTTPStatusCodes.ERROR.code, mockk())
        every {
            runBlocking {
                eventsApiService.event(EVENT_ID)
            }
        } throws HttpException(mockResponse)

        val eventsRepository = EventsRepository(eventsApiService)
        runBlocking {
            val response = eventsRepository.event(EVENT_ID)
            assert(response.code == HTTPStatusCodes.ERROR.code)
            assert(response.data == null)
        }
    }

    @Test
    fun checkinSuccess() {
        val checkin = Checkin(EVENT_ID, NAME, EMAIL)
        val mockResponse: CheckinResponse = Gson().fromJson(readJsonFile("checkin.json"), CheckinResponse::class.java)
        every {
            runBlocking {
                eventsApiService.checkin(checkin)
            }
        } returns mockResponse

        val eventsRepository = EventsRepository(eventsApiService)
        runBlocking {
            val response = eventsRepository.checkin(checkin)
            assert(response.code == HTTPStatusCodes.SUCCESS.code)
        }
    }

    companion object {
        const val EVENT_ID = "1"
        const val EMAIL = "Luis Fernando"
        const val NAME = "1"
    }
}