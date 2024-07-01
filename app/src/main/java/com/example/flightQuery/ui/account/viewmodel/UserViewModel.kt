package com.example.flightQuery.ui.account.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightQuery.data.member.UserDao
import com.example.flightQuery.domain.member.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userDao: UserDao) : ViewModel() {

    private val _userByName = MutableStateFlow<User?>(null)
    val userByName = _userByName.asStateFlow()

    fun registerInsertUser(user: User, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            val exists = userDao.isUsernameExists(user.username) ?: 0
            if (exists > 0) {
                callback(false)
            } else {
                userDao.insertUser(user)
                callback(true)
            }
        }
    }

    fun loginGetUser(username: String, password: String, callback: (User?) -> Unit) {
        viewModelScope.launch {
            val user = userDao.getUser(username, password)
            callback(user)
        }
    }

    fun loadUserByUsername(username: String) {
        viewModelScope.launch {
            val user = userDao.getUserByUsername(username)
            _userByName.value = user
        }
    }
}