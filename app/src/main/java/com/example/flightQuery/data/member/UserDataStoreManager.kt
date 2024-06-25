package com.example.flightQuery.data.member

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map


private const val USER_PREFERENCES_NAME = "user_preferences"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_PREFERENCES_NAME)

class UserDataStoreManager(private val dataStore: DataStore<Preferences>) {

    companion object {
        val USERNAME_KEY = stringPreferencesKey("username")
    }

    val usernameFlow: Flow<String> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[USERNAME_KEY] ?: ""
        }

    suspend fun saveUsername(username: String) {
        dataStore.edit { preferences ->
            preferences[USERNAME_KEY] = username
        }
    }

    suspend fun clearUsername() {
        dataStore.edit { preferences ->
            preferences[USERNAME_KEY] = ""
        }
    }
}