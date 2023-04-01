package com.ogzkesk.core.ext

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector

@Composable
fun LockScreenOrientation(orientation: Int,context: Context) {
    DisposableEffect(orientation) {
        val activity = context.findActivity()
        val originalOrientation = activity.requestedOrientation
        activity.requestedOrientation = orientation
        onDispose {
            activity.requestedOrientation = originalOrientation
        }
    }
}


/** works on StaggeredGridState, GridState, etc. */
@Composable
fun LazyListState.isScrollingUp(): Boolean {
    var previousIndex by remember(this) { mutableStateOf(firstVisibleItemIndex) }
    var previousScrollOffset by remember(this) { mutableStateOf(firstVisibleItemScrollOffset) }
    return remember(this) {
        derivedStateOf {
            if (previousIndex != firstVisibleItemIndex) {
                previousIndex > firstVisibleItemIndex
            } else {
                previousScrollOffset >= firstVisibleItemScrollOffset
            }.also {
                previousIndex = firstVisibleItemIndex
                previousScrollOffset = firstVisibleItemScrollOffset
            }
        }
    }.value
}


@SuppressLint("ComposableNaming")
@Composable
@NonRestartableComposable
fun <T> Flow<T>.collectInComposable(collector: FlowCollector<T>) {
    LaunchedEffect(key1 = collector) {
        collect(collector)
    }
}

@Composable
fun animateHorizontalBrushAsState(targetValue : Boolean, colorList1: List<Color>, colorList2: List<Color>) : Brush {

    if(colorList1.size != colorList2.size) throw Exception("Colorlist sizes should be same")

    val brushList = mutableListOf<Color>()
    colorList1.forEachIndexed { i, _ ->
        val brushItem = animateColorAsState(
            targetValue = if(targetValue){
                colorList1[i]
            } else {
                colorList2[i]
            }
        ).value
        brushList.add(brushItem)
    }

    return Brush.horizontalGradient(brushList)
}


/** for brush any item like text, icon tint etc.*/
@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.foregroundBrush(brush: Brush) = composed {
    Modifier
        .graphicsLayer(alpha = 0.99f)
        .drawWithCache {
            onDrawWithContent {
                drawContent()
                drawRect(brush, blendMode = BlendMode.SrcAtop)
            }
        }
}
