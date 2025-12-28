package com.example.pexelsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pexelsapp.ui.screens.MainScreen
import com.example.pexelsapp.ui.statesUi.PexelPageState
import com.example.pexelsapp.ui.theme.PexelsAppTheme
import com.example.pexelsapp.ui.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        actionBar?.hide()

        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition {
            val current = homeViewModel.uiState.value
//            android.util.Log.d("SplashState", "🌀 Current UI state: $current")
            current is PexelPageState.Loading
        }
        splashScreen.setOnExitAnimationListener { splashScreenViewProvider ->
            val splashView = splashScreenViewProvider.view
            splashView.animate()
                .rotation(720f)
                .scaleX(0f)
                .scaleY(0f)
                .setDuration(700L)
                .withEndAction {
                    splashScreenViewProvider.remove()
                }
                .start()
        }


        setContent {

            PexelsAppTheme {
                MainScreen(
                    homeViewModel
                )
            }
        }
    }
}
