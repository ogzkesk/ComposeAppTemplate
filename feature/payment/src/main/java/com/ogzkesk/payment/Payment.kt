package com.ogzkesk.payment

import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.ogzkesk.core.ui.navigation.Screen
import com.ogzkesk.core.ui.navigation.anim.*
import com.ogzkesk.core.ui.navigation.setup.navigator
import com.ogzkesk.core.ui.theme.mTitleLargeSemiBold
import com.ogzkesk.payment.content.PaymentTopBar

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.payment() {
    composable(
        Screen.Payment.route,
        enterTransition = { sheetEnterTransition() },
        exitTransition = { sheetExitTransition() },
        popEnterTransition = { sheetPopEnterTransition() },
        popExitTransition = { sheetPopExitTransition() }
    ) {
        Payment()
    }
}

@Composable
fun Payment() {

    val navigator = navigator
    val viewModel = hiltViewModel<PaymentViewModel>()
    BackHandler(enabled = true, onBack = {})

    PaymentScreen(
        onBackClick = navigator::popBackStack,
        onRestoreClick = viewModel::onRestoreSubscription
    )
}

@Composable
private fun PaymentScreen(
    onBackClick: () -> Unit,
    onRestoreClick: () -> Unit
) {

    Scaffold(
        topBar = {
            PaymentTopBar(
                onBackClick = onBackClick,
                onRestoreClick = onRestoreClick
            )
        }
    ) { padd ->

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                top = padd.calculateTopPadding(),
                bottom = 16.dp,
                start = 16.dp,
                end = 16.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item { Spacer(modifier = Modifier.height(24.dp)) }
            item {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(text = "PaymentScreen", style = mTitleLargeSemiBold)
                }
            }
        }
    }
}
