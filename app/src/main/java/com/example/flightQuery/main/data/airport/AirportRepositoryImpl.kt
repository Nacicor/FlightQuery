package com.example.flightQuery.main.data.airport

import com.example.flightQuery.main.domain.airport.model.AirportInfoItem
import retrofit2.Response

class AirportRepositoryImpl(private val apiService: AirportApiService) : AirportRepository {
    override suspend fun getThisTypeFlightInfo(currentFlightType: String): Response<List<AirportInfoItem>> {
        return apiService.getFlightAllInfo(currentFlightType, "TPE")
    }
}