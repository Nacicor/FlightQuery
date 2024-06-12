package com.example.flightQuery.main

import com.example.flightQuery.main.exchangerate.api.RateDataConvertor
import com.example.flightQuery.main.exchangerate.api.RateRepository
import com.example.flightQuery.main.exchangerate.viewModel.ExchangeRateViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val appModule = module {
    single { RateRepository() }
    single { RateDataConvertor() }
    viewModel { ExchangeRateViewModel(get(), get()) }
    viewModelOf(::ExchangeRateViewModel)
}
