package com.example.flightQuery.data.member

import android.content.Context
import androidx.room.Room.databaseBuilder

object LocalDatabaseInstance {
    private var instance: UserLocalDatabase? = null

    fun getDatabase(context: Context): UserLocalDatabase {
        return instance ?: synchronized(this) {
            val newInstance = databaseBuilder(
                context.applicationContext,
                UserLocalDatabase::class.java,
                "userLocalDatabase"
            ).build()
            instance = newInstance
            newInstance
        }
    }
}