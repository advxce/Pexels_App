package com.example.pexelsapp.ui.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.pexelsapp.ui.screens.main.MainScreen
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

        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition {
            val current = homeViewModel.uiState.value
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
