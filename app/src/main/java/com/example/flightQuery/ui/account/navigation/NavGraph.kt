package com.example.flightQuery.ui.account.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.flightQuery.ui.account.screen.AccountMainScreen
import com.example.flightQuery.ui.account.screen.LoginScreen
import com.example.flightQuery.ui.account.screen.RegisterScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Main") {
        composable("Main") {
            AccountMainScreen(navController)
        }
        composable("Login") {
            LoginScreen(navController)
        }
        composable("Register") {
            RegisterScreen(navController)
        }

    }

}