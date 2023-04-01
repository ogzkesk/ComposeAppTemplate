package com.ogzkesk.payment.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ogzkesk.core.R
import com.ogzkesk.core.ui.theme.mBodySmallSemiBold
import com.ogzkesk.core.ui.theme.mTitleMediumSemiBold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentTopBar(
    onBackClick: () -> Unit,
    onRestoreClick : () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name) + " " + 
                        stringResource(id = R.string.pro),
                style = mTitleMediumSemiBold
            )
        },
        actions = {
            FilledTonalIconButton(
                onClick = onBackClick,
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(id = R.string.back)
                )
            }
        },
        navigationIcon = {
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp))
            ) {
                Text(
                    text = stringResource(id = R.string.restore),
                    style = mBodySmallSemiBold,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 6.dp)
                )
            }
        }
    )
}