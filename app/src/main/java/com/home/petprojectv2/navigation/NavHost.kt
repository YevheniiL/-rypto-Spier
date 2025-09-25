package com.home.petprojectv2.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.scene.rememberSceneSetupNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.home.petprojectv2.presentation.ui.screens.SignInScreen
import com.home.petprojectv2.presentation.ui.screens.WelcomeScreen
import com.home.petprojectv2.presentation.ui.screens.splashscreen.SplashScreen

@Composable
fun NavHost(modifier: Modifier = Modifier) {
    val backStack = remember { mutableStateListOf<NavScreen>(NavScreen.Splash) }
    NavDisplay(
        entryDecorators =
            listOf(
                // Add the default decorators for managing scenes and saving state
                rememberSceneSetupNavEntryDecorator(),
                rememberSavedStateNavEntryDecorator(),
                // Then add the view model store decorator
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
