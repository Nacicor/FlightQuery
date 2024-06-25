package com.example.flightQuery.data.airport

import com.example.flightQuery.domain.airport.model.AirportInfoItem
import retrofit2.Response

class AirportRepositoryImpl(private val apiService: AirportApiService) : AirportRepository {
    override suspend fun getThisTypeFlightInfo(currentFlightType: String): Response<List<AirportInfoItem>> {
        return apiService.getFlightAllInfo(currentFlightType, "TPE")
    }
}