package com.ogzkesk.composeapptemplate.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.ogzkesk.composeapptemplate.util.SystemUiController
import com.ogzkesk.core.ui.navigation.Screen
import com.ogzkesk.core.ui.navigation.anim.*
import com.ogzkesk.core.ui.navigation.setup.ProvideNavHost
import com.ogzkesk.extra.addExtra
import com.ogzkesk.onboarding.addOnboarding
import com.ogzkesk.payment.addPayment
import com.ogzkesk.settings.addSettings
import com.ogzkesk.splash.addSplash

@OptIn(
    ExperimentalMaterialNavigationApi::class,
    ExperimentalAnimationApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun Root(isDarkModeOn: Boolean) {

    SystemUiController(isDarkModeOn = isDarkModeOn)

    val sheetState2 = rememberModalBottomSheetState()
    val bottomSheetNavigator = rememberBottomSheetNavigator()
    val navController = rememberAnimatedNavController(bottomSheetNavigator)


    Surface {
        ProvideNavHost(navHostController = navController) {
            ModalBottomSheetLayout(
                bottomSheetNavigator = bottomSheetNavigator,
                sheetBackgroundColor = MaterialTheme.colorScheme.surface,
                sheetShape = RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp
                ),
                sheetElevation = 24.dp,
            ) {
                AnimatedNavHost(
                    navController = navController,
                    startDestination = Screen.Splash.route,
                    enterTransition = { slideEnterTransition() },
                    exitTransition = { slideExitTransition() },
                    popEnterTransition = { slidePopEnterTransition() },
                    popExitTransition = { slidePopExitTransition() }
                ) {
                    addSplash()
                    addOnboarding()
                    addPayment()
                    addExtra()
                    addSettings()
                    addContainer()
                }
            }
        }
    }
}
