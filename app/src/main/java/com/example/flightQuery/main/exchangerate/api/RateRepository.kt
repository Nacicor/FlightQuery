package com.example.flightQuery.main.exchangerate.api

import retrofit2.Response

class RateRepository {
    suspend fun getFromApiResult(apiSource: RateClient): Response<RateInfoItem> {
        return apiSource.api.getRate("fca_live_TQcP4fiHbbuay9cZNFEFJ9v7mO7QYPyqTdkIZiRX")
    }
}