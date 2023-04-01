package com.ogzkesk.feed

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.ogzkesk.core.ui.navigation.TopLevelScreen
import com.ogzkesk.core.ui.theme.mTitleLargeSemiBold
import com.ogzkesk.feed.content.FeedTopBar

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addFeed(){
    composable(TopLevelScreen.Feed.route){
        Feed()
    }
}

@Composable
fun Feed() {

    FeedScreen()
}

@Composable
private fun FeedScreen() {
    Scaffold(
        topBar = { FeedTopBar() }
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
                    Text(text = "FeedScreen", style = mTitleLargeSemiBold)
                }
            }
        }
    }
}
