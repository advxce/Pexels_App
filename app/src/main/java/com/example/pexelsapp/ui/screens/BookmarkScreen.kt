package com.example.pexelsapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.pexelsapp.ui.components.EmptyBookmarks
import com.example.pexelsapp.ui.components.PhotographerImageTitleComponent
import com.example.pexelsapp.ui.theme.MulishBoldStyle
import com.example.pexelsapp.ui.viewModels.BookmarkViewModel

@Composable
fun BookmarkScreen(
    onOpenDetails: (Int) -> Unit,
    onGoHome: ()-> Unit,
    viewModel: BookmarkViewModel = hiltViewModel()
) {
    val photos = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadPhotos(1)
    }

    Column {
        Text(
            text = "Bookmarks",
            style = MulishBoldStyle,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        )
        if(photos.value.isEmpty()){
            EmptyBookmarks(
                goSearch = {
                    onGoHome()
                }
            )
        } else {
            PhotoListScreen(
                photos.value,
                onItemClick = {photo->
                    onOpenDetails(photo.id)
                },
                itemContent = { name->
                    PhotographerImageTitleComponent(name)
                }
            )
        }

    }

}