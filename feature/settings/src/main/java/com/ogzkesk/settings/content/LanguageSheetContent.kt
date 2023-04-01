package com.ogzkesk.settings.content

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.ogzkesk.settings.util.languagePrefs

@Composable
fun LanguageSheetContent(
    languagePref: Int,
    onItemClick: (titleRes: Int) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Divider(
            thickness = 3.dp,
            modifier = Modifier
                .clip(RoundedCornerShape(1.dp))
                .width(42.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        languagePrefs.forEach {

            SheetContent(
                selectedRow = languagePref,
                icon = it.icon,
                titleRes = it.titleRes,
                onItemClick = onItemClick
            )
        }
    }
}