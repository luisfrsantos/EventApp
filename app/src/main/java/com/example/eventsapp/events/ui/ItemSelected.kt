package com.example.eventsapp.events.ui

interface ItemSelected<T> {
    fun onItemSelected(events: T)
}