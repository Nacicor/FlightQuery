package com.example.flightQuery._di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.example.flightQuery.data.airport.AirportRepository
import com.example.flightQuery.data.airport.AirportRepositoryImpl
import com.example.flightQuery.data.exchange.RateRepository
import com.example.flightQuery.data.exchange.RateRepositoryImpl
import com.example.flightQuery.data.member.UserDataStoreManager
import com.example.flightQuery.data.member.UserLocalDatabase
import com.example.flightQuery.domain.airport.usecase.FetchAirportInfoUseCase
import com.example.flightQuery.domain.airport.usecase.GetAirportInfoResponseUseCase
import com.example.flightQuery.domain.exchange.usecase.CreateNewCurrencyMapUseCase
import com.example.flightQuery.domain.exchange.usecase.FetchCurrencyRateUseCase
import com.example.flightQuery.domain.exchange.usecase.GetRateResponseUseCase
import com.example.flightQuery.domain.exchange.usecase.IntegrateResultToMapUseCase
import com.example.flightQuery.ui.account.viewmodel.LoginViewModel
import com.example.flightQuery.ui.account.viewmodel.UserViewModel
import com.example.flightQuery.ui.airport.AirportInfoViewModel
import com.example.flightQuery.ui.exchange.ExchangeRateViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

private const val USER_PREFERENCES_NAME = "user_preferences"

val Context.dataStore by preferencesDataStore(name = USER_PREFERENCES_NAME)

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

    single { get<UserLocalDatabase>().userDao() }
    viewModel { UserViewModel(get()) }

    single { androidContext().dataStore }
    single { UserDataStoreManager(get()) }

    viewModel { LoginViewModel(get()) }
}
