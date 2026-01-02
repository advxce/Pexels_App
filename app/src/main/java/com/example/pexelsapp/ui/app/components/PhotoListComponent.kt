package com.example.pexelsapp.ui.app.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pexelsapp.ui.entities.PhotoUi

@Composable
fun PhotoListComponent(
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