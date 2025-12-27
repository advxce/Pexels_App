package com.example.pexelsapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.pexelsapp.ui.components.EmptyHome
import com.example.pexelsapp.ui.components.NetworkStub
import com.example.pexelsapp.ui.components.SearchAndCategoriesBar
import com.example.pexelsapp.ui.statesUi.PexelPageState
import com.example.pexelsapp.ui.viewModels.HomeViewModel


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onOpenDetails: (Int) -> Unit
) {
    val uiState = viewModel.uiState.collectAsState()
    val collections = viewModel.featuredCollections.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getPhotos(1)
        viewModel.getCollection()
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {


        SearchAndCategoriesBar(
            categories = collections.value,
            isLoading = uiState.value is PexelPageState.Loading,
            onSearch = { query ->
                if (query.isNotBlank()) {
                    viewModel.getFilteredPhotos(query)
                } else {
                    viewModel.getPhotos(1)
                }
            },
            onClear = {
                viewModel.getPhotos(1)
            }
        )

        Box(modifier = Modifier.fillMaxSize()) {
            when (val state = uiState.value) {
                is PexelPageState.Loading -> {}
                is PexelPageState.Error -> NetworkStub(tryConnect = {
                    viewModel.getPhotos(1)
                })

                is PexelPageState.isEmpty -> {
                    EmptyHome(
                        onExplore = { viewModel.getPhotos(1) }
                    )
                }

                is PexelPageState.Success -> {
                    PhotoListScreen(
                        photoList = state.list,
                        onItemClick = { photo ->
                            onOpenDetails(photo.id)
                        })
                }
            }
        }
    }


}

