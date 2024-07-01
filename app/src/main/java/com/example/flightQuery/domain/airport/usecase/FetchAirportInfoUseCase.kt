package com.example.flightQuery.domain.airport.usecase

import com.example.flightQuery.data.airport.AirportRepository
import com.example.flightQuery.domain.airport.model.AirportInfoItem
import retrofit2.Response
import javax.inject.Inject

class FetchAirportInfoUseCase @Inject constructor(private val repository: AirportRepository) {
    suspend fun execute(currentFlightType: String): Response<List<AirportInfoItem>> {
        return repository.getThisTypeFlightInfo(currentFlightType)
    }
}