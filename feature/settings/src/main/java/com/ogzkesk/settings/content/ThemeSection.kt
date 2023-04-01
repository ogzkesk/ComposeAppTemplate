package com.ogzkesk.settings.content

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ogzkesk.core.R
import com.ogzkesk.core.ui.theme.MOrange
import com.ogzkesk.core.ui.theme.isSystemDarkTheme
import com.ogzkesk.core.ui.theme.mBodyMediumLight

@Composable
fun ThemeSection(onClick: (titleRes: Int) -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp))
            .clickable { onClick(R.string.change_theme) }
            .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            imageVector = Icons.Default.DarkMode,
            contentDescription = null,
            tint = if (isSystemDarkTheme()) {
                MOrange
            } else {
                MaterialTheme.colorScheme.onSurface
            }
        )
        Text(
            text = stringResource(id = R.string.change_theme),
            style = mBodyMediumLight
        )
    }
}
