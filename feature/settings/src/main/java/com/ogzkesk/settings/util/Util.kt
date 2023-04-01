package com.ogzkesk.settings.util

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.ogzkesk.core.R

enum class SheetContent {
    THEME,
    LANGUAGE,
}

data class RowItem(
    @StringRes val titleRes: Int,
    val icon: ImageVector
)

val themePrefs = listOf(
    RowItem(titleRes = R.string.dark_mode, icon = Icons.Default.DarkMode),
    RowItem(titleRes = R.string.light_mode, icon = Icons.Default.LightMode),
    RowItem(titleRes = R.string.system_default, icon = Icons.Default.Devices)
)

val languagePrefs = listOf(
    RowItem(titleRes = R.string.english, icon = Icons.Default.Language),
    RowItem(titleRes = R.string.system_default, icon = Icons.Default.Devices),
)


val generalRowList by lazy {
    listOf(
        RowItem(titleRes = R.string.share_app, icon = Icons.Default.IosShare),
        RowItem(titleRes = R.string.rate_us, icon = Icons.Default.StarRate),
        RowItem(titleRes = R.string.language, icon = Icons.Default.Translate),
    )
}

val legalRowList = listOf(
    RowItem(titleRes = R.string.privacy_policy, icon = Icons.Default.PrivacyTip),
    RowItem(titleRes = R.string.terms_of_use, icon = Icons.Default.Assignment)
)