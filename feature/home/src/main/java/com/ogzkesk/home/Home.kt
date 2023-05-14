package com.ogzkesk.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.ogzkesk.core.ui.navigation.Screen
import com.ogzkesk.core.ui.navigation.TopLevelScreen
import com.ogzkesk.core.ui.navigation.setup.navigator
import com.ogzkesk.core.ui.theme.mTitleLargeSemiBold
import com.ogzkesk.domain.util.ThemePref
import com.ogzkesk.home.content.HomeTopBar

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.home() {
    composable(TopLevelScreen.Home.route) {
        Home()
    }
}

@Composable
fun Home() {

    val navigator = navigator
    val viewModel = hiltViewModel<HomeViewModel>()
    val themePref by viewModel.readThemePref.collectAsStateWithLifecycle()
    val event by viewModel.event.collectAsState(initial = false)

    when(event){
        is HomeViewModel.Event.ShowPayWall -> {
            navigator.navigate(Screen.Payment.route)
        }
    }


    HomeScreen(
        themePref = themePref,
        onProClick = navigator::navigate,
        onThemeChange = viewModel::onThemeChange
    )
}

@Composable
private fun HomeScreen(
    themePref: ThemePref,
    onThemeChange: (Boolean) -> Unit,
    onProClick: (String) -> Unit
) {

    Scaffold(
        topBar = {
            HomeTopBar(
                themePref = themePref,
                onThemeChange = onThemeChange,
                onProClick = onProClick
            )
        }
    ) { padd ->
        LazyColumn(
            contentPadding = PaddingValues(
                top = padd.calculateTopPadding(),
                bottom = 16.dp,
                start = 16.dp,
                end = 16.dp
            )
        ) {
            item { Spacer(modifier = Modifier.height(24.dp)) }

            item {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(text = "HomeScreen", style = mTitleLargeSemiBold)
                }
            }
        }
    }
}