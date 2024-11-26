package com.montenegro.movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.montenegro.movies.ui.theme.MoviesTheme
import com.montenegro.movies.ui.view.MyConstraintLayout
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import androidx.compose.runtime.SideEffect
import androidx.compose.foundation.isSystemInDarkTheme
import android.view.View

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

        setContent {
            MoviesTheme {
                val systemUiController = rememberSystemUiController()
                val useDarkIcons = !isSystemInDarkTheme()

                // Configura la UI del sistema para ocultar la barra de estado
                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = useDarkIcons
                    )
                }

                Surface(color = Color(0xFFF0F0F0)) {
                    MyConstraintLayout()
                }
            }
        }
    }
}
