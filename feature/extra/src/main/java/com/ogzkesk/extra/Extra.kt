package com.ogzkesk.extra

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.ogzkesk.core.ui.navigation.Screen
import com.ogzkesk.core.ui.navigation.anim.*
import com.ogzkesk.core.ui.navigation.setup.navigator
import com.ogzkesk.core.ui.theme.mTitleLargeSemiBold
import com.ogzkesk.extra.content.ExtraTopBar

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.extra(){
    composable(route = Screen.Extra.route){
        Extra()
    }
}

@Composable
fun Extra() {

    val navigator = navigator
    val viewModel = hiltViewModel<ExtraViewModel>()

    ExtraScreen(
        onBackClick = navigator::popBackStack
    )
}


@Composable
private fun ExtraScreen(
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            ExtraTopBar(
                onBackClick = onBackClick
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
                Text(text = "ExtraScreen", style = mTitleLargeSemiBold)
            }
        }
    }
}