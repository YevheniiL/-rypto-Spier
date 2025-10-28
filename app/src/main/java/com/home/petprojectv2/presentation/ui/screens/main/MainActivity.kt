package com.home.petprojectv2.presentation.ui.screens.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.home.petprojectv2.navigation.NavHost
import com.home.petprojectv2.presentation.theme.PetProjectV2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        // Handle the splash screen transition
        val splashScreen = installSplashScreen()
        // Keep the splash screen visible until the data is loaded
        splashScreen.setKeepOnScreenCondition {
            !viewModel.isDataLoaded.value
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PetProjectV2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    NavHost(modifier = Modifier)
                }
            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PetProjectV2Theme {
        Greeting("Android")
    }
}
