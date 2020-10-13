package com.example.eventsapp.events.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.eventsapp.commons.HTTPStatusCodes
import com.example.eventsapp.events.model.Checkin
import com.example.eventsapp.events.model.CheckinResponse
import com.example.eventsapp.events.model.Events
import com.example.eventsapp.events.repository.EventsRepository

class EventsViewModel @ViewModelInject constructor(
    private val eventsRepository: EventsRepository
) : ViewModel() {

    fun loadEventsData(): LiveData<ViewData<List<Events>>> = liveData {
        val response = eventsRepository.eventList()
        emit(ViewData<List<Events>>(ViewState.LOADING))
        when (response.code) {
            HTTPStatusCodes.SUCCESS.code -> {
                emit(ViewData(ViewState.SUCCESS, response.data))
            }
            HTTPStatusCodes.ERROR.code -> {
                emit(ViewData(ViewState.ERROR, response.data, response.message))
            }
        }
    }

    fun loadEventData(id: String): LiveData<ViewData<Events>> = liveData {
        val response = eventsRepository.event(id)
        when (response.code) {
            HTTPStatusCodes.SUCCESS.code -> {
                emit(ViewData(ViewState.SUCCESS, response.data))
            }
            HTTPStatusCodes.ERROR.code -> {
                emit(ViewData(ViewState.ERROR, response.data, response.message))
            }
        }
    }

    fun checkin(checkin: Checkin): LiveData<ViewData<CheckinResponse>> = liveData {
        val response = eventsRepository.checkin(checkin)
        when (response.code) {
            HTTPStatusCodes.SUCCESS.code -> {
                emit(ViewData(ViewState.SUCCESS, response.data))
            }
            HTTPStatusCodes.ERROR.code -> {
                emit(ViewData(ViewState.ERROR, response.data, response.message))
            }
        }
    }
}