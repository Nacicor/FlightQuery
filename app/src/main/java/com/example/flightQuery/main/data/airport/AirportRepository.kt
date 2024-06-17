package com.example.flightQuery.main.data.airport

import com.example.flightQuery.main.domain.airport.model.AirportInfoItem
import retrofit2.Response

interface AirportRepository {
    suspend fun getThisTypeFlightInfo(currentFlightType: String): Response<List<AirportInfoItem>>
}