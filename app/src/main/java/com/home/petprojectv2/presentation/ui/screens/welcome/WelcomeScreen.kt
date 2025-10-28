package com.home.petprojectv2.presentation.ui.screens.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    onNavSignIn: () -> Unit,
) {
    Column(
        modifier =
            modifier
                .fillMaxSize()
                .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Welcome Screen", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {
            // 5. Navigate to the profile screen when the button is clicked.
            // We use the route defined in our sealed class.
            onNavSignIn()
        }) {
            Text("Go to Profile")
        }
    }
}
