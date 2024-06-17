package com.example.flightQuery.main.data.exchange

import com.example.flightQuery.main.domain.exchange.model.RateInfoItem
import retrofit2.Response

interface RateRepository {
    suspend fun getFromApiResult(): Response<RateInfoItem>
}