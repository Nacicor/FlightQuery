package com.example.flightQuery.main.airportinfo.api

import android.util.Log

class AirportRepository {
    suspend fun FlightInfo(currentFlightType: String): List<AirportInfoItem> {
        try {
            AirportClient.api.getFlightAllInfo(currentFlightType, "TPE").let { response ->
                if (response.isSuccessful) {
                    return response.body() ?: emptyList()
                } else {
                    Log.d(
                        "DataViewModel",
                        "onFailure: ${response.message()} , code : ${response.code()}"
                    )
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return emptyList()
    }
}