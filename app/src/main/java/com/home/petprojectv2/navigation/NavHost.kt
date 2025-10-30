package com.home.petprojectv2.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.home.petprojectv2.presentation.ui.screens.signin.SignInScreen
import com.home.petprojectv2.presentation.ui.screens.splash.SplashScreen
import com.home.petprojectv2.presentation.ui.screens.welcome.WelcomeScreen

@Composable
fun NavHost(modifier: Modifier = Modifier) {
    val backStack = remember { mutableStateListOf<NavScreen>(NavScreen.Splash) }
    NavDisplay(
        entryDecorators =
            listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator(),
            ),
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                is NavScreen.Splash ->
                    NavEntry(key) {
                        SplashScreen(
                            modifier = modifier,
                            onFinish = {
                                // Navigate to the Welcome screen and remove the Splash screen from the back stack
                                backStack.clear()
                                backStack.add(NavScreen.Welcome)
                            },
                        )
                    }
                is NavScreen.Welcome ->
                    NavEntry(key) {
                        WelcomeScreen(
                            modifier = modifier,
                            onNavSignIn = {
                                backStack.clear()
                                backStack.add(NavScreen.SignIn)
                            },
                        )
                    }
                is NavScreen.SignIn ->
                    NavEntry(key) {
                        SignInScreen(modifier)
                    }
            }
        },
    )
}
