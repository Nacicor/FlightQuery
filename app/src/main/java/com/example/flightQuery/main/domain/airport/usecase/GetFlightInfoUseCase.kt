package com.example.flightQuery.main.domain.airport.usecase

import com.example.flightQuery.main.data.airport.AirportRepository
import com.example.flightQuery.main.domain.airport.model.AirportInfoItem

class GetFlightInfoUseCase(private val airportRepository: AirportRepository) {
    suspend fun execute(flightType: String): List<AirportInfoItem> {
        return airportRepository.flightInfo(flightType)
    }
}