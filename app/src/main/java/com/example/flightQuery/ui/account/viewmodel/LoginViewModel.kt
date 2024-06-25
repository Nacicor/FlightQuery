package com.example.flightQuery.ui.account.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightQuery.data.member.UserDataStoreManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val userPreferences: UserDataStoreManager) : ViewModel() {

    private val _username = MutableStateFlow("")
    val username = _username.asStateFlow()

    init {
        viewModelScope.launch {
            userPreferences.usernameFlow.collect { username ->
                _username.value = username
            }
        }
    }

    fun saveUsername(username: String) {
        viewModelScope.launch {
            userPreferences.saveUsername(username)
        }
    }

    fun clearUsername() {
        viewModelScope.launch {
            userPreferences.clearUsername()
        }
    }
}