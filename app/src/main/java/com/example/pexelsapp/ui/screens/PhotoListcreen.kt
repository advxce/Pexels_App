package com.example.pexelsapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import com.example.pexelsapp.ui.components.PhotoComponent
import com.example.pexelsapp.ui.components.ShimmerPlaceholder
import com.example.pexelsapp.ui.entities.PhotoUi

@Composable
fun PhotoListScreen(
    photoList: List<PhotoUi>,
    onItemClick: (PhotoUi) -> Unit,
    itemContent: (@Composable (String) -> Unit)? = null
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalItemSpacing = 12.dp,
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 24.dp, end = 24.dp, top = 8.dp)
    ) {
        items(items = photoList) { photo ->
            Box(
                modifier = Modifier
                    .clickable { onItemClick(photo) }

            ) {
                PhotoComponent(url = photo.src)

                Box(
                    modifier = Modifier.matchParentSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    itemContent?.invoke(photo.photographer)
                }
            }

        }
    }
}