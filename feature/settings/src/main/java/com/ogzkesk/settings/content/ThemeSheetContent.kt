package com.ogzkesk.settings.content

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.ogzkesk.core.R
import com.ogzkesk.domain.util.ThemePref
import com.ogzkesk.settings.util.themePrefs


@Composable
fun ThemeSheetContent(
    themePref: ThemePref,
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

        themePrefs.forEach {

            val selectedRow = when(themePref){
                ThemePref(darkMode = true,systemDef = false) -> R.string.dark_mode
                ThemePref(darkMode = false,systemDef = false) -> R.string.light_mode
                ThemePref(darkMode = false,systemDef = true) -> R.string.system_default
                else -> R.string.dark_mode
            }

            SheetContent(
                selectedRow = selectedRow,
                icon = it.icon,
                titleRes = it.titleRes,
                onItemClick = onItemClick
            )
        }
    }
}