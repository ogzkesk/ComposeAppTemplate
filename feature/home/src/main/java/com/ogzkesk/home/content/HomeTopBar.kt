package com.ogzkesk.home.content

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ogzkesk.core.R
import com.ogzkesk.core.ui.navigation.Screen
import com.ogzkesk.core.ui.theme.MOrange
import com.ogzkesk.core.ui.theme.mBodyLargeSemiBold
import com.ogzkesk.core.ui.theme.mTitleMediumSemiBold
import com.ogzkesk.core.util.setThemePref
import com.ogzkesk.domain.util.ThemePref

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    themePref: ThemePref,
    onThemeChange : (Boolean) -> Unit,
    onProClick : (String) -> Unit
) {

    var uiPref = setThemePref(themePref = themePref)

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.home),
                style = mTitleMediumSemiBold
            )
        },
        actions = {
            IconButton(
                onClick = {
                    uiPref = !uiPref
                    onThemeChange(uiPref)
                }
            ) {
                Crossfade(targetState = uiPref) { pref ->
                    Icon(
                        imageVector = Icons.Default.DarkMode,
                        contentDescription = null,
                        tint = if (pref) MOrange else MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Box(
                modifier = Modifier
                    .padding(12.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.surfaceColorAtElevation(6.dp))
                    .clickable { onProClick(Screen.Payment.route) }
            ) {
                Text(
                    text = stringResource(id = R.string.pro),
                    style = mBodyLargeSemiBold,
                    color = MOrange,
                    modifier = Modifier.padding(vertical = 6.dp, horizontal = 8.dp)
                )
            }
        }
    )
}