package com.ogzkesk.profile

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
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
import com.ogzkesk.core.ui.navigation.TopLevelScreen
import com.ogzkesk.core.ui.navigation.setup.navigator
import com.ogzkesk.core.ui.theme.mTitleLargeSemiBold
import com.ogzkesk.profile.content.ProfileTopBar

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addProfile(){
    composable(TopLevelScreen.Profile.route){
        Profile()
    }
}

@Composable
fun Profile(viewModel: ProfileViewModel = hiltViewModel()) {

    val navigator = navigator

    ProfileScreen(
        onSettingsClick = navigator::navigate,
        onNavigateToExtraScreen = navigator::navigate
    )
}

@Composable
fun ProfileScreen(
    onSettingsClick: (String) -> Unit,
    onNavigateToExtraScreen: (String) -> Unit
) {
    Scaffold(
        topBar = {
            ProfileTopBar(
                onSettingsClick = onSettingsClick
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
        ){
            item { Spacer(modifier = Modifier.height(24.dp)) }

            item {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(text = "ProfileScreen", style = mTitleLargeSemiBold)
                }
            }

            item {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Button(onClick = { onNavigateToExtraScreen(Screen.Extra.route) }) {
                        Text(text = "Go To ExtraScreen")
                    }
                }
            }
        }
    }
}
