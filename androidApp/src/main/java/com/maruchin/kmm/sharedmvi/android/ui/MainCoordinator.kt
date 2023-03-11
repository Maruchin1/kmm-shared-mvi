package com.maruchin.kmm.sharedmvi.android.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

private const val LOGIN = "login"
private const val HOME = "home"

@Composable
fun MainCoordinator() {
    val navController = rememberNavController()
    NavHost(navController, LOGIN) {
        composable(LOGIN) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(HOME) {
                        popUpTo(LOGIN) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(HOME) {
            HomeScreen(
                onLogoutSuccess = {
                    navController.navigate(LOGIN) {
                        popUpTo(HOME) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}
