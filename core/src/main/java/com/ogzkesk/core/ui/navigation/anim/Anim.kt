package com.ogzkesk.core.ui.navigation.anim

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.IntSize
import androidx.navigation.NavBackStackEntry

@OptIn(ExperimentalAnimationApi::class)
fun AnimatedContentScope<NavBackStackEntry>.enterTransition() : EnterTransition {
    return slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(300))
}

@OptIn(ExperimentalAnimationApi::class)
fun AnimatedContentScope<NavBackStackEntry>.exitTransition() : ExitTransition {
    return slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(300))
}

@OptIn(ExperimentalAnimationApi::class)
fun AnimatedContentScope<NavBackStackEntry>.popEnterTransition() : EnterTransition {
    return slideIntoContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(300))
}

@OptIn(ExperimentalAnimationApi::class)
fun AnimatedContentScope<NavBackStackEntry>.popExitTransition() : ExitTransition {
    return slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(300))
}

@OptIn(ExperimentalAnimationApi::class)
fun AnimatedContentScope<NavBackStackEntry>.sheetEnterTransition() : EnterTransition {
    return slideIntoContainer(AnimatedContentScope.SlideDirection.Up)
}

@OptIn(ExperimentalAnimationApi::class)
fun AnimatedContentScope<NavBackStackEntry>.sheetExitTransition() : ExitTransition {
    return slideOutOfContainer(AnimatedContentScope.SlideDirection.Up)
}

@OptIn(ExperimentalAnimationApi::class)
fun AnimatedContentScope<NavBackStackEntry>.sheetPopEnterTransition() : EnterTransition {
    return slideIntoContainer(AnimatedContentScope.SlideDirection.Down)
}

@OptIn(ExperimentalAnimationApi::class)
fun AnimatedContentScope<NavBackStackEntry>.sheetPopExitTransition() : ExitTransition {
    return slideOutOfContainer(AnimatedContentScope.SlideDirection.Down)
}

fun slideExitTransition() : ExitTransition {
    return slideOutHorizontally(
        targetOffsetX = { -300 },
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        )
    ) + fadeOut(animationSpec = tween(300))
}

fun slidePopEnterTransition() : EnterTransition {
    return slideInHorizontally(
        initialOffsetX = { -300 },
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        )
    ) + fadeIn(animationSpec = tween(300))
}

fun slideEnterTransition() : EnterTransition {
    return slideInHorizontally(
        initialOffsetX = { 300 },
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        )
    ) + fadeIn(animationSpec = tween(300))
}

fun slidePopExitTransition() : ExitTransition {
    return slideOutHorizontally(
        targetOffsetX = { 300 },
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        )
    ) + fadeOut(animationSpec = tween(300))
}


