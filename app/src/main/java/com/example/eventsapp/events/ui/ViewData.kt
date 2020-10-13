package com.example.eventsapp.events.ui

data class ViewData<T>(val state: ViewState, val data: T? = null, val message: String? = null)

enum class ViewState {
    LOADING,
    SUCCESS,
    ERROR
}