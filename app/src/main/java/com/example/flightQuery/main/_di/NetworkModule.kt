package com.example.flightQuery.main._di

import com.example.flightQuery.main.data.airport.AirportRetrofitInstance
import com.example.flightQuery.main.data.exchange.RateRetrofitInstance
import org.koin.dsl.module

val networkModule = module {
    single { RateRetrofitInstance.api }

    single { AirportRetrofitInstance.api }
}