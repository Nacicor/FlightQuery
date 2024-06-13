package com.example.flightQuery.main.data.airport

import android.util.Log
import com.example.flightQuery.main.domain.airport.model.AirportInfoItem

class AirportRepository {
    suspend fun flightInfo(currentFlightType: String): List<AirportInfoItem> {
        try {
            AirportRetrofitInstance.api.getFlightAllInfo(currentFlightType, "TPE").let { response ->
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