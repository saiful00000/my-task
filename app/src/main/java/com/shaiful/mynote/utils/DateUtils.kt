package com.shaiful.mynote.utils

import java.time.DayOfWeek
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DateUtils @Inject constructor() {

    fun getCurrentWeekDates(): List<LocalDate> {
        val currentDate = LocalDate.now()
        val startOfWeek = currentDate.with(DayOfWeek.MONDAY) // Start of the week
        val endOfWeek = currentDate.with(DayOfWeek.SUNDAY)  // End of the week

        return (0..6).map { offset ->
            startOfWeek.plusDays(offset.toLong())
        }
    }
}