package com.example.pexelsapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import coil3.compose.AsyncImage
import com.example.pexelsapp.ui.statesUi.PhotoState

@Composable
fun PhotoContainer(
    state: PhotoState.Success,
    onBookmarkClick: ()-> Unit
) {
    Column {
        Text(text = state.photo.photographer)
        AsyncImage(
            model = state.photo.src,
            contentDescription = null
        )
        Row {
            Image(
                imageVector = Icons.Default.Download,
                contentDescription = null
            )
            IconButton(onClick = onBookmarkClick) {
                Icon(
                    imageVector = if (state.photo.bookmarked)
                        Icons.Default.Favorite
                    else
                        Icons.Default.FavoriteBorder,
                    contentDescription = null
                )
            }
        }
    }
}