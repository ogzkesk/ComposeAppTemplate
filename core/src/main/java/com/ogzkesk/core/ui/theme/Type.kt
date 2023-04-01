package com.ogzkesk.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val mTitleLargeSemiBold = Typography.titleLarge.copy(fontWeight = FontWeight.SemiBold)
val mTitleMediumSemiBold = Typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
val mTitleMediumLight = Typography.titleMedium.copy(fontWeight = FontWeight.Light)
val mBodyMediumLight = Typography.bodyMedium.copy(fontWeight = FontWeight.Light)
val mBodySmallLight = Typography.bodySmall.copy(fontWeight = FontWeight.Light)
val mBodyLargeLight = Typography.bodyLarge.copy(fontWeight = FontWeight.Light)
val mBodyMediumSemiBold = Typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
val mBodySmallSemiBold = Typography.bodySmall.copy(fontWeight = FontWeight.SemiBold)
val mBodyLargeSemiBold = Typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold)