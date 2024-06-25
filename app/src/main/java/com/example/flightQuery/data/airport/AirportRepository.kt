package com.example.flightQuery.data.airport

import com.example.flightQuery.domain.airport.model.AirportInfoItem
import retrofit2.Response

interface AirportRepository {
    suspend fun getThisTypeFlightInfo(currentFlightType: String): Response<List<AirportInfoItem>>
}