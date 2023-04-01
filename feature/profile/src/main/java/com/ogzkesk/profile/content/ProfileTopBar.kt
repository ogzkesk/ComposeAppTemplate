package com.ogzkesk.profile.content

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ogzkesk.core.R
import com.ogzkesk.core.ui.navigation.Screen
import com.ogzkesk.core.ui.theme.mTitleMediumSemiBold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTopBar(
    onSettingsClick: (String) -> Unit
) {

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.profile),
                style = mTitleMediumSemiBold
            )
        },
        actions = {
            FilledTonalIconButton(
                onClick = { onSettingsClick(Screen.Settings.route) },
                colors = IconButtonDefaults.filledTonalIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
                )
            ) {
                Icon(
                    imageVector = Icons.Rounded.Settings,
                    contentDescription = stringResource(id = R.string.settings)
                )
            }
        }
    )
}