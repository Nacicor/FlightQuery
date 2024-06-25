package com.example.flightQuery.data.member

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.flightQuery.domain.member.User

@Database(entities = [User::class], version = 1)
abstract class UserLocalDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}