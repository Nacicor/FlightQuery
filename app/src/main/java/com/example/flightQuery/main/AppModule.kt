package com.example.flightQuery.main

import com.example.flightQuery.main.data.airport.AirportRepository
import com.example.flightQuery.main.data.airport.AirportRetrofitInstance
import com.example.flightQuery.main.data.exchange.RateRepository
import com.example.flightQuery.main.domain.airport.usecase.GetFlightInfoUseCase
import com.example.flightQuery.main.domain.exchange.usecase.ConvertRateDataUseCase
import com.example.flightQuery.main.domain.exchange.usecase.CreateNewCurrencyMapUseCase
import com.example.flightQuery.main.ui.airport.AirportInfoViewModel
import com.example.flightQuery.main.ui.exchange.ExchangeRateViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val appModule = module {
    single { RateRepository() }
    single { ConvertRateDataUseCase() }
    single { CreateNewCurrencyMapUseCase() }
    viewModel { ExchangeRateViewModel(get(), get(), get()) }
    viewModelOf(::ExchangeRateViewModel)

    single { AirportRetrofitInstance.api }
    single { AirportRepository(get()) } // 注入 AirportApiService
    single { GetFlightInfoUseCase(get()) }
    viewModel { AirportInfoViewModel(get()) }
    viewModelOf(::AirportInfoViewModel)
}
