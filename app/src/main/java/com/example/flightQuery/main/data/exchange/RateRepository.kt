package com.example.flightQuery.main.data.exchange

import com.example.flightQuery.main.domain.exchange.model.RateInfoItem
import retrofit2.Response

class RateRepository {
    suspend fun getFromApiResult(apiSource: RateRetrofitInstance): Response<RateInfoItem> {
        return apiSource.api.getRate("fca_live_TQcP4fiHbbuay9cZNFEFJ9v7mO7QYPyqTdkIZiRX")
    }
}