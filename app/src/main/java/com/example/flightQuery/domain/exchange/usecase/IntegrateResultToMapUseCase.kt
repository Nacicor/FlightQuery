package com.example.flightQuery.domain.exchange.usecase

class IntegrateResultToMapUseCase(
    private val fetchCurrencyRateUseCase: FetchCurrencyRateUseCase,
    private val getRateResponseUseCase: GetRateResponseUseCase
) {
    suspend fun getResult(): Map<String, Double> {
        val response = fetchCurrencyRateUseCase.execute()
        return getRateResponseUseCase.convertResponseToMap(response)
            .mapValues { (_, value) -> value as? Double ?: 1.0 }
    }
}