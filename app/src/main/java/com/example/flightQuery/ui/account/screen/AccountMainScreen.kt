package com.example.flightQuery.ui.account.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.flightQuery.ui.account.viewmodel.LoginViewModel
import com.example.flightQuery.ui.account.viewmodel.UserViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AccountMainScreen(
    navController: NavController,
    userViewModel: UserViewModel = koinViewModel(),
    loginViewModel: LoginViewModel = koinViewModel()
) {
    val userFromDataStore by loginViewModel.username.collectAsState()
    val userFromDatabase by userViewModel.userByName.collectAsState()

    //First time if local data is not logging , then if userFromDatabase is not null then get the user data from database
    LaunchedEffect(userFromDataStore) {
        if (userFromDataStore.isNotEmpty()) {
            userViewModel.loadUserByUsername(userFromDataStore)
        }
    }

    if (userFromDataStore.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { navController.navigate("Login") }) {
                Text("Login")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { navController.navigate("Register") }) {
                Text("Register")
            }
        }
    } else {
        userFromDatabase?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Welcome, ${it.name}")
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    loginViewModel.clearUsername()
                    navController.navigate("Main") {
                        popUpTo("Main") { inclusive = true }
                    }
                }) {
                    Text("Logout")
                }
            }
        } ?: run {
            Text("Loading...")
        }
    }

}