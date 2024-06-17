package com.example.flightQuery.main.domain.exchange.usecase

import com.example.flightQuery.main.data.exchange.RateRepository
import com.example.flightQuery.main.domain.exchange.model.RateInfoItem
import retrofit2.Response

class FetchCurrencyRateUseCase(
    private val repository: RateRepository,
) {
    suspend fun execute(): Response<RateInfoItem> {
        return repository.getFromApiResult()
    }
}