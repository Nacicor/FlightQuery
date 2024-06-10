package com.example.cathaybankairport.main.airportinfo.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://e-traffic.taichung.gov.tw/DataAPI/"

object AirportClient {
    private val retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val api: AirportAPI = retrofit.create(AirportAPI::class.java)
}