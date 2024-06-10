package com.example.cathaybankairport.main.airportinfo.api

import com.google.gson.annotations.SerializedName

data class AirportInfoItem(
    @SerializedName("ActualTime")
    val actualTime: String? = "00:10",
    @SerializedName("Airline")
    val airLine: String? = "00:02",
    @SerializedName("AirlineID")
    val airLineId: String? = "123",
    @SerializedName("ArrivalAirport")
    val arrivalAirport: String? = "123",
    @SerializedName("ArrivalAirportID")
    val arrivalAirportID: String? = "123",
    @SerializedName("DepartureAirport")
    val departureAirport: String? = "123",
    @SerializedName("DepartureAirportID")
    val departureAirportID: String? = "123",
    @SerializedName("EstimatedTime")
    val estimatedTime: String? = "123",
    @SerializedName("FlightNumber")
    val flightNumber: String? = "123",
    @SerializedName("FlyType")
    val flyType: String? = "123",
    @SerializedName("Gate")
    val gate: String? = "123",
    @SerializedName("Remark")
    val remark: String = "123",
    @SerializedName("ScheduleTime")
    val scheduleTime: String? = "123",
    @SerializedName("Terminal")
    val terminal: String? = "123",
    @SerializedName("UpdateTime")
    val updateTime: String? = "123"
) {
    val status: AirportStatus get() = AirportStatus.fromValue(remark)

}