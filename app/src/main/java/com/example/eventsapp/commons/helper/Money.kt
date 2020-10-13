package com.example.eventsapp.commons.helper

import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

object Money {
    fun toMoney(value: Double): String {
        val format: NumberFormat = NumberFormat.getCurrencyInstance()
        format.maximumFractionDigits = 2
        format.currency = Currency.getInstance(Locale.getDefault())

        return format.format(value)
    }
}
