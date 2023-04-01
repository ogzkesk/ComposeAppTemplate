package com.ogzkesk.feed.content

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.ogzkesk.core.R
import com.ogzkesk.core.ui.theme.mTitleMediumSemiBold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedTopBar() {

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.feed),
                style = mTitleMediumSemiBold
            )
        }
    )
}