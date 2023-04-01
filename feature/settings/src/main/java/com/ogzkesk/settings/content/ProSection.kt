package com.ogzkesk.settings.content

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ogzkesk.core.R
import com.ogzkesk.core.ui.navigation.Screen
import com.ogzkesk.core.ui.theme.MBrush
import com.ogzkesk.core.ui.theme.MDarkWhite
import com.ogzkesk.core.ui.theme.mBodyMediumLight
import com.ogzkesk.core.ui.theme.mBodySmallSemiBold

@Composable
fun UpgradeSection(onProClick:(String) -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp))
            .clickable { onProClick(Screen.Payment.route) }
            .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.premium),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = stringResource(id = R.string.upgrade),
            style = mBodyMediumLight
        )
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .background(MBrush)
        ) {
            Text(
                text = stringResource(id = R.string.pro),
                style = mBodySmallSemiBold,
                color = MDarkWhite,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}