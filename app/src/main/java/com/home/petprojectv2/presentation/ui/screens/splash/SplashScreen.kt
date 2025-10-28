package com.home.petprojectv2.presentation.ui.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onFinish: () -> Unit,
) {
    // Display splash screen content
    Box(
        modifier = modifier.fillMaxSize().background(Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Text("Pet Project V2", fontSize = 32.sp)
    }

    // Use a LaunchedEffect to trigger navigation when the screen is "done"
    LaunchedEffect(key1 = Unit) {
        // Simulate data loading
        delay(5000L)
        onFinish()
    }
}
