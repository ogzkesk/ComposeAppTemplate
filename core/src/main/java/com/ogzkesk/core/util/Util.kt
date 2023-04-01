package com.ogzkesk.core.util

import android.graphics.Bitmap
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.ogzkesk.domain.util.ThemePref

@Composable
fun setThemePref(themePref: ThemePref): Boolean {
    return when {
        themePref.systemDef -> isSystemInDarkTheme()
        themePref.darkMode -> true
        else -> false
    }
}

fun makeSmallerBitmap(image: Bitmap, maximumSize: Int): Bitmap {
    var width = image.width
    var height = image.height
    val bitmapRatio: Double = width.toDouble() / height.toDouble()

    if (bitmapRatio > 1) {
        width = maximumSize
        val scaledHeight = width / bitmapRatio
        height = scaledHeight.toInt()

    } else {
        height = maximumSize
        val scaledWidth = height * bitmapRatio
        width = scaledWidth.toInt()

    }
    return Bitmap.createScaledBitmap(image, width, height, true)

}

