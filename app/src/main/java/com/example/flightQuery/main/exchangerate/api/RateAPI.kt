package com.example.flightQuery.main.exchangerate.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface RateAPI {
    @GET("v1/latest")
    suspend fun getRate(
        @Header("apiKey") apiKey: String
    ): Response<RateInfoItem>
}