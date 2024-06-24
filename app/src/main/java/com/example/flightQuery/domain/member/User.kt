package com.example.flightQuery.domain.member

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey val username: String,
    val password: String,
    val name: String? = "Unknown user",
    val avatarUrl: String? = ""
)