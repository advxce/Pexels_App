package com.example.pexelsapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.pexelsapp.ui.components.ErrorComponent
import com.example.pexelsapp.ui.components.SearchAndCategoriesBar
import com.example.pexelsapp.ui.entities.PexelPageState
import com.example.pexelsapp.ui.viewModels.HomeViewModel


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
){
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
            categories = collections.value?.collections,
            isLoading = uiState.value is PexelPageState.Loading,
            onSearch = { query ->
                if (query.isNotBlank()) {
                    viewModel.getFilteredPhotos(query)
                } else {
                    viewModel.getPhotos(1)
                }
            }
        )
//        FeaturedListComponent(collections.value?.collections){collection->
//            viewModel.getFilteredPhotos(collection.title)
//        }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            when (val state = uiState.value) {
                is PexelPageState.Loading -> {}
                is PexelPageState.Error -> ErrorComponent(state.msg)
                is PexelPageState.Success -> PhotoListScreen(photoList = state.list.photos)
            }
        }
    }

}