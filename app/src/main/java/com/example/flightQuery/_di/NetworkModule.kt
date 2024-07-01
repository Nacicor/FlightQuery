package com.example.flightQuery._di

import android.content.Context
import com.example.flightQuery.data.airport.AirportApiService
import com.example.flightQuery.data.airport.AirportRetrofitInstance
import com.example.flightQuery.data.exchange.RateApiService
import com.example.flightQuery.data.exchange.RateRetrofitInstance
import com.example.flightQuery.data.member.LocalDatabaseInstance
import com.example.flightQuery.data.member.UserLocalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRateRetrofitApi(): RateApiService {
        return RateRetrofitInstance.api
    }

    @Provides
    @Singleton
    fun provideAirportRetrofitApi(): AirportApiService {
        return AirportRetrofitInstance.api
    }

    @Provides
    @Singleton
    fun provideLocalDatabase(@ApplicationContext context: Context): UserLocalDatabase {
        return LocalDatabaseInstance.getDatabase(context)
    }
}