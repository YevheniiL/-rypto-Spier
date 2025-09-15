package com.home.petprojectv2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.home.petprojectv2.presentation.ui.screens.SplashScreen
import com.home.petprojectv2.presentation.ui.screens.WelcomeScreen
import kotlinx.serialization.Serializable

class NavScreens {
    @Serializable
    object SplashScreen

    @Serializable
    object OnboardingScreen
}

@Composable
fun Controller() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavScreens.SplashScreen) {
        composable<NavScreens.SplashScreen> { SplashScreen(navController) }
        composable<NavScreens.OnboardingScreen> { WelcomeScreen() }
    }
}
