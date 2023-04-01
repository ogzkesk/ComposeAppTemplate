package com.ogzkesk.core.ui.navigation

import androidx.annotation.DrawableRes
import com.ogzkesk.core.R


sealed class Screen(val route: String) {

    object Splash : Screen("splash")

    object Onboarding : Screen("onboarding")

    object Settings : Screen("settings")

    object Extra : Screen("extra")

    object Payment : Screen("payment")

}

sealed class TopLevelScreen(
    val route: String,
    val title: String,
    @DrawableRes val iconOutlined: Int,
    @DrawableRes val iconFilled: Int
) {

    object Home : TopLevelScreen(
        route = "home_route",
        title = "Home",
        iconOutlined = R.drawable.outline_home_24,
        iconFilled = R.drawable.filled_home_24
    )

    object Feed : TopLevelScreen(
        route = "feed_route",
        title = "Feed",
        iconOutlined = R.drawable.outline_feed_24,
        iconFilled = R.drawable.filled_feed_24
    )

    object Profile : TopLevelScreen(
        route = "profile_route",
        title = "Profile",
        iconOutlined = R.drawable.outline_profile_24,
        iconFilled = R.drawable.filled_profile_24
    )
}

sealed class BottomSheets(val route: String) {

}

object Container {
    const val HOME = "home_container"
}