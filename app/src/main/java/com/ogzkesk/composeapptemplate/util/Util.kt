package com.ogzkesk.composeapptemplate.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SystemUiController(isDarkModeOn: Boolean) {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isDarkModeOn

    SideEffect {
        systemUiController.apply {
            setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = useDarkIcons,
                isNavigationBarContrastEnforced = false,
            )
            setStatusBarColor(
                color = Color.Transparent,
                darkIcons = useDarkIcons
            )
        }
    }
}

