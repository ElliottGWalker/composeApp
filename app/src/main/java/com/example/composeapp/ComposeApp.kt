package com.example.composeapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun ComposeApp() {
    val navController = rememberNavController()
//    DestinationsNavHost(navGraph = )
}

@Composable
fun ComposeAppNavHost(navController: NavHostController) {
}
