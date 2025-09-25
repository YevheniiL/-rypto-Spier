package com.home.petprojectv2.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface NavScreen : NavKey {
    @Serializable
    object Splash : NavScreen

    @Serializable
    object Welcome : NavScreen

    @Serializable
    object SignIn : NavScreen
}
