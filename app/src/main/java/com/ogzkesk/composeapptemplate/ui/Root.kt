package com.ogzkesk.composeapptemplate.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ogzkesk.composeapptemplate.util.SystemUiController
import com.ogzkesk.core.ui.navigation.Screen
import com.ogzkesk.core.ui.navigation.anim.*
import com.ogzkesk.core.ui.navigation.setup.ProvideNavHost
import com.ogzkesk.extra.extra
import com.ogzkesk.onboarding.onboarding
import com.ogzkesk.payment.payment
import com.ogzkesk.settings.settings
import com.ogzkesk.splash.splash

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Root(isDarkModeOn: Boolean) {

    SystemUiController(isDarkModeOn = isDarkModeOn)

    val navController = rememberAnimatedNavController()


    Surface {
        ProvideNavHost(navHostController = navController) {
            AnimatedNavHost(
                navController = navController,
                startDestination = Screen.Splash.route,
                enterTransition = { slideEnterTransition() },
                exitTransition = { slideExitTransition() },
                popEnterTransition = { slidePopEnterTransition() },
                popExitTransition = { slidePopExitTransition() }
            ) {
                splash()
                onboarding()
                payment()
                extra()
                settings()
                container()
            }
        }
    }
}
