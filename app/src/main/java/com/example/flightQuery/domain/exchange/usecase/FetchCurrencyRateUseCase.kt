package com.example.flightQuery.domain.exchange.usecase

import com.example.flightQuery.data.exchange.RateRepository
import com.example.flightQuery.domain.exchange.model.RateInfoItem
import retrofit2.Response
import javax.inject.Inject

class FetchCurrencyRateUseCase @Inject constructor(
    private val repository: RateRepository,
) {
    suspend fun execute(): Response<RateInfoItem> {
        return repository.getFromApiResult()
    }
}