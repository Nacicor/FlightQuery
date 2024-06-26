package com.example.flightQuery.ui.account.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.flightQuery.ui.account.screen.AccountMainScreen
import com.example.flightQuery.ui.account.screen.LoginScreen
import com.example.flightQuery.ui.account.screen.RegisterScreen

@Composable
fun AccountNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AccountPage.Main.name) {
        composable(AccountPage.Main.name) {
            AccountMainScreen(navController)
        }
        composable(AccountPage.Login.name) {
            LoginScreen(navController)
        }
        composable(AccountPage.Register.name) {
            RegisterScreen(navController) {}
        }
    }

}