package com.example.flightQuery.main.data.exchange

import com.example.flightQuery.main.domain.exchange.model.RateInfoItem
import retrofit2.Response

class RateRepositoryImpl(private val apiService: RateApiService) : RateRepository {
    override suspend fun getFromApiResult(): Response<RateInfoItem> {
        return apiService.getRate("fca_live_TQcP4fiHbbuay9cZNFEFJ9v7mO7QYPyqTdkIZiRX")
    }
}