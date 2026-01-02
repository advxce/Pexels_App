package com.example.pexelsapp.ui.app.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import kotlinx.coroutines.launch

@Composable
fun PhotoComponent(
    url: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    isZoomable: Boolean = false
) {
    val scale = remember { Animatable(1f) }
    val coroutineScope = rememberCoroutineScope()

    var appliedModifier = modifier
        .fillMaxWidth()
        .heightIn(min = 180.dp)
        .clip(RoundedCornerShape(20.dp))

    if (isZoomable) {
        appliedModifier = appliedModifier
            .scale(scale.value)
            .pointerInput(Unit) {
                detectTransformGestures { _, _, zoom, _ ->
                    val newScale = (scale.value * zoom).coerceIn(1f, 4f)
                    coroutineScope.launch {
                        scale.snapTo(newScale)
                    }
                    if (zoom == 1f) {
                        coroutineScope.launch {
                            scale.animateTo(
                                targetValue = 1f,
                                animationSpec = tween(durationMillis = 300)
                            )
                        }
                    }
                }
            }
    }

    SubcomposeAsyncImage(
        model = url,
        contentDescription = null,
        contentScale = contentScale,
        modifier = appliedModifier,
        loading = { ShimmerPlaceholder(Modifier.matchParentSize()) },
        error = { ShimmerPlaceholder(Modifier.matchParentSize()) }
    )


}