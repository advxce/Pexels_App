package com.example.pexelsapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.pexelsapp.R
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
    val context = LocalContext.current
    val uiState = viewModel.uiState.collectAsState()
    val collections = viewModel.featuredCollections.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getPhotos(1, context)
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
                    viewModel.getFilteredPhotos(query, context)
                } else {
                    viewModel.getPhotos(1, context)
                }
            },
            onClear = {
                viewModel.getPhotos(1, context)
            }
        )

        Box(modifier = Modifier.fillMaxSize()) {
            when (val state = uiState.value) {
                is PexelPageState.Loading -> {}
                is PexelPageState.Error -> {
                    Toast.makeText(context, state.msg, Toast.LENGTH_SHORT).show()
                    NetworkStub(tryConnect = {
                        viewModel.retry(context)
                    })
                }

                is PexelPageState.IsEmpty -> {
                    EmptyHome(
                        onExplore = { viewModel.getPhotos(1, context) }
                    )
                }

                is PexelPageState.Success -> {
                    PhotoListScreen(
                        photoList = state.list,
                        onItemClick = { photo ->
                            onOpenDetails(photo.id)
                        })
                }

                PexelPageState.NoConnectionWithCache -> {
                    Toast.makeText(
                        context,
                        stringResource(R.string.no_connection_but_enable_cache),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }


}

