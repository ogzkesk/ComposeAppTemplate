package com.ogzkesk.splash

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import com.ogzkesk.core.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.navigation.animation.composable
import com.ogzkesk.core.ui.navigation.Container
import com.ogzkesk.core.ui.navigation.Screen
import com.ogzkesk.core.ui.navigation.setup.navigator
import kotlinx.coroutines.delay

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.splash(){
    composable(Screen.Splash.route){
        Splash()
    }
}


@Composable
fun Splash() {

    val navigator = navigator
    val viewModel = hiltViewModel<SplashViewModel>()
    val isAppStarted by viewModel.isAppStarted.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        delay(2000)
        if(isAppStarted){
            navigator.popBackStack()
            navigator.navigate(Container.HOME)
        } else {
            navigator.popBackStack()
            navigator.navigate(Screen.Onboarding.route)
        }
    }

    SplashScreen()

}

@Composable
private fun SplashScreen(){

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.logo_anim))
    val progress by animateLottieCompositionAsState(composition)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF160545)),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier.fillMaxWidth(0.4f)
        )
    }
}