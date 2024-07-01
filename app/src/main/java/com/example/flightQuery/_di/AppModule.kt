package com.example.flightQuery._di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.example.flightQuery.data.airport.AirportApiService
import com.example.flightQuery.data.airport.AirportRepository
import com.example.flightQuery.data.airport.AirportRepositoryImpl
import com.example.flightQuery.data.exchange.RateApiService
import com.example.flightQuery.data.exchange.RateRepository
import com.example.flightQuery.data.exchange.RateRepositoryImpl
import com.example.flightQuery.data.member.UserDao
import com.example.flightQuery.data.member.UserDataStoreManager
import com.example.flightQuery.data.member.UserLocalDatabase
import com.example.flightQuery.data.member.dataStore
import com.example.flightQuery.domain.airport.usecase.FetchAirportInfoUseCase
import com.example.flightQuery.domain.airport.usecase.GetAirportInfoResponseUseCase
import com.example.flightQuery.domain.exchange.usecase.CreateNewCurrencyMapUseCase
import com.example.flightQuery.domain.exchange.usecase.FetchCurrencyRateUseCase
import com.example.flightQuery.domain.exchange.usecase.GetRateResponseUseCase
import com.example.flightQuery.domain.exchange.usecase.IntegrateResultToMapUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//private const val USER_PREFERENCES_NAME = "user_preferences"
//
//val Context.dataStore by preferencesDataStore(name = USER_PREFERENCES_NAME)


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//    @Provides
//    @Singleton
//    fun provideUserLocalDatabase(@ApplicationContext context: Context): UserLocalDatabase {
//        return Room.databaseBuilder(
//            context,
//            UserLocalDatabase::class.java,
//            "user_database"
//        ).build()
//    }

    @Provides
    fun provideUserDao(userLocalDatabase: UserLocalDatabase): UserDao {
        return userLocalDatabase.userDao()
    }

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dataStore
    }

    @Provides
    @Singleton
    fun provideUserDataStoreManager(dataStore: DataStore<Preferences>): UserDataStoreManager {
        return UserDataStoreManager(dataStore)
    }

    @Provides
    @Singleton
    fun provideAirportRepository(api: AirportApiService): AirportRepository {
        return AirportRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideRateRepository(api: RateApiService): RateRepository {
        return RateRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideFetchAirportInfoUseCase(repository: AirportRepository): FetchAirportInfoUseCase {
        return FetchAirportInfoUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetAirportInfoResponseUseCase(): GetAirportInfoResponseUseCase {
        return GetAirportInfoResponseUseCase()
    }

    @Provides
    @Singleton
    fun provideFetchCurrencyRateUseCase(repository: RateRepository): FetchCurrencyRateUseCase {
        return FetchCurrencyRateUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideIntegrateResultToMapUseCase(
        fetchCurrencyRateUseCase: FetchCurrencyRateUseCase,
        getRateResponseUseCase: GetRateResponseUseCase
    ): IntegrateResultToMapUseCase {
        return IntegrateResultToMapUseCase(fetchCurrencyRateUseCase, getRateResponseUseCase)
    }

    @Provides
    @Singleton
    fun provideCreateNewCurrencyMapUseCase(): CreateNewCurrencyMapUseCase {
        return CreateNewCurrencyMapUseCase()
    }

    @Provides
    @Singleton
    fun provideGetRateResponseUseCase(): GetRateResponseUseCase {
        return GetRateResponseUseCase()
    }
}
