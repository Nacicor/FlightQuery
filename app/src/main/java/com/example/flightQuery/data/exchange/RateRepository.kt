package com.example.flightQuery.data.exchange

import com.example.flightQuery.domain.exchange.model.RateInfoItem
import retrofit2.Response

interface RateRepository {
    suspend fun getFromApiResult(): Response<RateInfoItem>
}