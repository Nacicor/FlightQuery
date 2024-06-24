package com.example.flightQuery._di

import com.example.flightQuery.data.airport.AirportRepository
import com.example.flightQuery.data.airport.AirportRepositoryImpl
import com.example.flightQuery.data.exchange.RateRepository
import com.example.flightQuery.data.exchange.RateRepositoryImpl
import com.example.flightQuery.domain.airport.usecase.FetchAirportInfoUseCase
import com.example.flightQuery.domain.airport.usecase.GetAirportInfoResponseUseCase
import com.example.flightQuery.domain.exchange.usecase.CreateNewCurrencyMapUseCase
import com.example.flightQuery.domain.exchange.usecase.FetchCurrencyRateUseCase
import com.example.flightQuery.domain.exchange.usecase.GetRateResponseUseCase
import com.example.flightQuery.domain.exchange.usecase.IntegrateResultToMapUseCase
import com.example.flightQuery.ui.airport.AirportInfoViewModel
import com.example.flightQuery.ui.exchange.ExchangeRateViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val appModule = module {

    single<AirportRepository> { AirportRepositoryImpl(get()) }
    single { FetchAirportInfoUseCase(get()) }
    single { GetAirportInfoResponseUseCase() }
    viewModel { AirportInfoViewModel(get(), get()) }
    viewModelOf(::AirportInfoViewModel)

    single<RateRepository> { RateRepositoryImpl(get()) }
    single { GetRateResponseUseCase() }
    single { CreateNewCurrencyMapUseCase() }
    single { FetchCurrencyRateUseCase(get()) }
    single { IntegrateResultToMapUseCase(get(), get()) }
    viewModel { ExchangeRateViewModel(get(), get()) }
    viewModelOf(::ExchangeRateViewModel)


}
