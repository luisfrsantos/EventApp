package com.example.eventsapp.commons.helper

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Date

object DateHelper {
    private const val PATTERN = "MM/dd/yyyy"
    fun toDate(value: Long): String =
        SimpleDateFormat(PATTERN, Locale.getDefault()).format(Date(value))
}