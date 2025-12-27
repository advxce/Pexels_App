package com.example.pexelsapp.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.pexelsapp.R
import com.example.pexelsapp.ui.components.BookmarkComponent
import com.example.pexelsapp.ui.components.DownloadButton
import com.example.pexelsapp.ui.components.EmptyDetails
import com.example.pexelsapp.ui.components.NetworkStub
import com.example.pexelsapp.ui.components.ProgressIndicatorComponent
import com.example.pexelsapp.ui.components.TopBar
import com.example.pexelsapp.ui.statesUi.PhotoState
import com.example.pexelsapp.ui.viewModels.PhotoDetailsViewModel

@Composable
fun PhotoDetailsScreen(
    photoId: Int,
    onBack: () -> Unit,
    viewModel: PhotoDetailsViewModel = hiltViewModel(),
    goToHome: ()-> Unit
) {
    val photoState = viewModel.photoState.collectAsState()
    val localContext = LocalContext.current


    LaunchedEffect(photoId) {
       viewModel.loadPhoto(photoId)
    }
    Box(modifier = Modifier.fillMaxSize()) {
        when (val state= photoState.value) {
            is PhotoState.Error -> NetworkStub(
                tryConnect = {
                    viewModel.loadPhoto(photoId)
                }
            )
            is PhotoState.isEmpty->{
                EmptyDetails(
                    onExplore = {
                        goToHome()
                    }
                )
            }
            PhotoState.Loading -> {
                Column(modifier = Modifier.fillMaxSize()) {
                    TopBar(
                        photographerName = stringResource(R.string.default_author),
                        onBack = onBack,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )
                    ProgressIndicatorComponent()
                }
            }

            is PhotoState.Success -> {
                Column(modifier = Modifier.fillMaxSize()) {
                    TopBar(
                        photographerName = state.photo.photographer,
                        onBack = onBack,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        AsyncImage(
                            model = state.photo.src,
                            contentDescription = "Photo",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(20.dp)),
                            contentScale = ContentScale.Fit
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp)
                    ) {
                        DownloadButton(
                            onClick = {
                                val photo = (photoState.value as? PhotoState.Success)?.photo
                                    ?: return@DownloadButton
                                viewModel.downloadImage(context = localContext, url = photo.src)
                            }
                        )

                        Log.i("Book", "${state.photo.bookmarked}")
                        BookmarkComponent(
                            isBookmarked = state.photo.bookmarked,
                            onClick = { viewModel.updateBookmark(state.photo) }
                        )
                    }

                }
            }
        }
    }


}



