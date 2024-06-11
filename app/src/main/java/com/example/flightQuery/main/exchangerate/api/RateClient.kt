package com.example.flightQuery.main.exchangerate.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.freecurrencyapi.com/"

object RateClient {
    private val retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val api: RateAPI = retrofit.create(RateAPI::class.java)
}