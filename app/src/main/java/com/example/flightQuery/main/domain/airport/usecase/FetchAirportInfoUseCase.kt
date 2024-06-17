package com.example.flightQuery.main.domain.airport.usecase

import com.example.flightQuery.main.data.airport.AirportRepository
import com.example.flightQuery.main.domain.airport.model.AirportInfoItem
import retrofit2.Response

class FetchAirportInfoUseCase(private val repository: AirportRepository) {
    suspend fun execute(currentFlightType: String): Response<List<AirportInfoItem>> {
        return repository.getThisTypeFlightInfo(currentFlightType)
    }
}