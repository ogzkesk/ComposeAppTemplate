package com.ogzkesk.settings.content

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.app.ShareCompat
import com.ogzkesk.core.R
import com.ogzkesk.core.ui.theme.MOrange

@Composable
fun SheetContent(
    selectedRow: Int,
    icon: ImageVector,
    titleRes: Int,
    onItemClick: (titleRes: Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clickable { onItemClick(titleRes) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.padding(start = 16.dp),
            imageVector = icon,
            contentDescription = stringResource(id = titleRes),
            tint = if (selectedRow == titleRes) MOrange else MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            modifier = Modifier.padding(end = 16.dp),
            text = stringResource(id = titleRes),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
        )
        Spacer(modifier = Modifier.weight(1f))
        if (selectedRow == titleRes) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = Color.Green.copy(alpha = 0.6f),
                modifier = Modifier.padding(end = 16.dp)
            )
        }
    }
}

fun shareApp(context: Context,appLink : String){
    ShareCompat.IntentBuilder(context)
        .setType("text/plain")
        .setChooserTitle("Share With")
        .setText("App Name")
        .setText(appLink)
        .startChooser();
}