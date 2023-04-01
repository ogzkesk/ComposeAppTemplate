package com.ogzkesk.composeapptemplate.ui

import android.content.res.Configuration
import android.graphics.drawable.GradientDrawable.Orientation
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.ogzkesk.core.ui.navigation.Container
import com.ogzkesk.core.ui.navigation.Screen
import com.ogzkesk.core.ui.navigation.TopLevelScreen
import com.ogzkesk.core.ui.navigation.bottom_bar.BottomBar
import com.ogzkesk.core.ui.navigation.bottom_bar.RailBar
import com.ogzkesk.core.ui.navigation.setup.navigator
import com.ogzkesk.feed.addFeed
import com.ogzkesk.home.addHome
import com.ogzkesk.profile.addProfile

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addContainer() {
    composable(route = Container.HOME) {
        Container()
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Container() {

    val navController = rememberAnimatedNavController()
    val conf = LocalConfiguration.current

    PermanentNavigationDrawer(
        drawerContent = {
            if (conf.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                Row {
                    RailBar(navController = navController)
                    Divider(
                        thickness = 0.4.dp,
                        modifier = Modifier
                            .width(0.4.dp)
                            .fillMaxHeight()
                    )
                }
            }
        }
    ) {
        Scaffold(
            bottomBar = {
                if (conf.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    Column {
                        Divider(
                            thickness = 0.4.dp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(0.4.dp)
                        )
                        BottomBar(navController = navController)
                    }
                }
            },
        ) {
            AnimatedNavHost(
                route = Container.HOME,
                navController = navController,
                startDestination = TopLevelScreen.Home.route,
                modifier = Modifier.padding(
                    bottom = it.calculateBottomPadding(),
                    start = it.calculateStartPadding(LayoutDirection.Rtl)
                )
            ) {
                addHome()
                addProfile()
                addFeed()
            }
        }
    }
}