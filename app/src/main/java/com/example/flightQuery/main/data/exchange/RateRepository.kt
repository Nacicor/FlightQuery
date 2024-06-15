package com.example.flightQuery.main.data.exchange

import com.example.flightQuery.main.domain.exchange.model.RateInfoItem
import retrofit2.Response

class RateRepository(private val apiService: RateApiService) {
    suspend fun getFromApiResult(): Response<RateInfoItem> {
        return apiService.getRate("fca_live_TQcP4fiHbbuay9cZNFEFJ9v7mO7QYPyqTdkIZiRX")
    }
}