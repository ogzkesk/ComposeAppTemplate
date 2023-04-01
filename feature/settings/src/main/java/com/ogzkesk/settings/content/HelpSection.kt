package com.ogzkesk.settings.content

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HelpCenter
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ogzkesk.core.R
import com.ogzkesk.core.ui.theme.mBodyMediumLight

@Composable
fun HelpSection(onHelpClick : () -> Unit ) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp))
            .clickable { onHelpClick() }
            .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            imageVector = Icons.Default.HelpCenter,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = stringResource(id = R.string.contact_us),
            style = mBodyMediumLight
        )
    }
}