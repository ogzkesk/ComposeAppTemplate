package com.ogzkesk.settings.content

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ogzkesk.core.ui.theme.mBodyMediumLight
import com.ogzkesk.settings.util.RowItem

@Composable
fun LegalSection(
    rowItem: RowItem,
    onLegalClick: (titleRes : Int) -> Unit,
    index : Int
) {


    val shape = when(index){
        0 -> RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
        1 -> RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
        else -> RectangleShape
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .background(MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp))
            .clickable { onLegalClick(rowItem.titleRes) }
            .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            imageVector = rowItem.icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = stringResource(id = rowItem.titleRes),
            style = mBodyMediumLight
        )
    }

}