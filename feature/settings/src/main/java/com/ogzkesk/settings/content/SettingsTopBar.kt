package com.ogzkesk.settings.content

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.ogzkesk.core.ui.theme.mTitleMediumSemiBold
import com.ogzkesk.core.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsTopBar(onBackClick: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.settings),
                style = mTitleMediumSemiBold
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIos,
                    contentDescription = stringResource(id = R.string.back)
                )
            }
        }
    )
}