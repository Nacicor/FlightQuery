package com.example.flightQuery.domain.airport.usecase

import com.example.flightQuery.domain.airport.model.AirportInfoItem
import retrofit2.Response

class GetAirportInfoResponseUseCase {
    fun processResponse(response: Response<List<AirportInfoItem>>): List<AirportInfoItem> {
        return if (response.isSuccessful) {
            response.body() ?: emptyList()
        } else {
            emptyList()
        }
    }
}