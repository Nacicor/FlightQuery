package com.example.flightQuery._di

import com.example.flightQuery.data.airport.AirportRetrofitInstance
import com.example.flightQuery.data.exchange.RateRetrofitInstance
import com.example.flightQuery.data.member.LocalDatabaseInstance
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val networkModule = module {
    single { RateRetrofitInstance.api }

    single { AirportRetrofitInstance.api }

    single { LocalDatabaseInstance.getDatabase(androidContext()) }
}