package com.example.flightQuery.data.airport

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object AirportRetrofitInstance {
    private const val BASE_URL = "https://e-traffic.taichung.gov.tw/DataAPI/"

    val api: AirportApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(AirportApiService::class.java)
    }
}