package com.ogzkesk.core.ui.navigation.bottom_bar

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ogzkesk.core.R
import com.ogzkesk.core.ui.navigation.TopLevelScreen
import com.ogzkesk.core.ui.theme.isSystemDarkTheme

@Composable
fun BottomBar(navController: NavHostController) {

    val insets = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()

    val screens = listOf(
        TopLevelScreen.Home,
        TopLevelScreen.Feed,
        TopLevelScreen.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        BottomAppBar(
            tonalElevation = 0.dp,
            contentPadding = PaddingValues(top = 10.dp),
            contentColor = MaterialTheme.colorScheme.surface,
            modifier = Modifier
                .height(if (insets > 25.dp) 96.dp else 64.dp),
            actions = {

                screens.forEach {
                    AddBottomBarItem(
                        screen = it,
                        currentDestination = currentDestination,
                        navController = navController
                    )
                }
            }
        )
    }
}

@Composable
fun RailBar(navController: NavHostController) {

    val screens = listOf(
        TopLevelScreen.Home,
        TopLevelScreen.Feed,
        TopLevelScreen.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        NavigationRail(
            header = {
                Icon(
                    modifier = Modifier.padding(vertical = 32.dp),
                    painter = painterResource(id = R.drawable.app_logo),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        ) {
            screens.forEach { screen ->
                AddRailItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun ColumnScope.AddRailItem(
    screen: TopLevelScreen,
    currentDestination: NavDestination?,
    navController: NavHostController,
) {

    val selected = currentDestination?.hierarchy?.any {
        it.route == screen.route
    } == true

    NavigationRailItem(
        selected = selected,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                restoreState = true
                launchSingleTop = true
            }
        },
        icon = {
            Crossfade(targetState = selected) {

                val painter = if(it){
                    painterResource(screen.iconFilled)
                } else {
                    painterResource(screen.iconOutlined)
                }

                Icon(
                    painter = painter,
                    contentDescription = screen.title,
                )
            }
        }
    )
}

@Composable
fun RowScope.AddBottomBarItem(
    screen: TopLevelScreen,
    currentDestination: NavDestination?,
    navController: NavHostController,
) {
    val selected = currentDestination?.hierarchy?.any {
        it.route == screen.route
    } == true

    val iconColor by animateColorAsState(
        targetValue = if (selected) {
            if (isSystemDarkTheme()) Color.White else Color.Black
        } else {
            MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
        }
    )

    BottomNavigationItem(
        alwaysShowLabel = false,
        selected = selected,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                restoreState = true
                launchSingleTop = true
            }
        },
        icon = {
            Crossfade(targetState = selected) {

                val painter = if(it){
                    painterResource(screen.iconFilled)
                } else {
                    painterResource(screen.iconOutlined)
                }

                Icon(
                    painter = painter,
                    contentDescription = screen.title,
                    tint = iconColor
                )
            }
        }
    )
}