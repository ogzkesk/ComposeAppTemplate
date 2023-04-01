package com.ogzkesk.settings.content

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.IosShare
import androidx.compose.material.icons.filled.RateReview
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ogzkesk.core.R
import com.ogzkesk.core.ui.theme.mBodyMediumLight
import com.ogzkesk.settings.util.RowItem

@Composable
fun GeneralRowSection(
    rowItem: RowItem,
    index: Int,
    onClick: (titleRes: Int) -> Unit
) {
    val shape = when(index){
        0 -> RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
        2 -> RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
        else -> RectangleShape
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .background(MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp))
            .clickable { onClick(rowItem.titleRes) }
            .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            imageVector = rowItem.icon,
            contentDescription = null,
        )
        Text(
            text = stringResource(id = rowItem.titleRes),
            style = mBodyMediumLight
        )
    }

    if(index != 2){
        Divider()
    }
}
