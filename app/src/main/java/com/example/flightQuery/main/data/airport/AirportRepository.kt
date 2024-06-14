package com.example.flightQuery.main.data.airport

import android.util.Log
import com.example.flightQuery.main.domain.airport.model.AirportInfoItem

class AirportRepository(private val apiService: AirportApiService) {
    suspend fun flightInfo(currentFlightType: String): List<AirportInfoItem> {
        try {
            val response = apiService.getFlightAllInfo(currentFlightType, "TPE")
            if (response.isSuccessful) {
                return response.body() ?: emptyList()
            } else {
                Log.d(
                    "AirportRepository",
                    "onFailure: ${response.message()} , code : ${response.code()}"
                )
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return emptyList()
    }
}