package com.example.flightQuery.main

import android.app.Application
import com.example.flightQuery.main._di.appModule
import com.example.flightQuery.main._di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(appModule, networkModule)
        }
    }
}