package com.example.cathaybankairport.main.airportinfo

import com.example.cathaybankairport.R

enum class AirportStatus(val color: Int, val value: String) {
    Arrived(R.color.teal_700, "已到ARRIVED"),
    OnTime(R.color.blue, "準時ON TIME"),
    Departed(R.color.green, "出發DEPARTED"),
    ScheduleChange(R.color.brown, "時間更改SCHEDULE CHANGE"),
    Canceled(R.color.red, "取消CANCELLED");

    companion object {
        fun fromValue(value: String): AirportStatus {
            return entries.find {
                it.value == value
            } ?: Canceled
        }
    }
}