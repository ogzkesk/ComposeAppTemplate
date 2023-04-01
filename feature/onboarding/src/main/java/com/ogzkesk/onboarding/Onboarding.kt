package com.ogzkesk.onboarding

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.ogzkesk.core.ui.navigation.Container
import com.ogzkesk.core.ui.navigation.Screen
import com.ogzkesk.core.ui.navigation.setup.navigator
import com.ogzkesk.core.ui.theme.mTitleLargeSemiBold

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addOnboarding(){
    composable(Screen.Onboarding.route){
        Onboarding()
    }
}

@Composable
fun Onboarding(viewModel: OnboardingViewModel = hiltViewModel()) {

    val navigator = navigator

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Onboarding Screen", style = mTitleLargeSemiBold)
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {
            navigator.popBackStack()
            navigator.navigate(Container.HOME)
        }) {
            Text(text = "Go Home")
        }
    }
}

