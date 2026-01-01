package com.example.pexelsapp.ui.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pexelsapp.domain.usecases.GetFeaturedCollectionsUseCase
import com.example.pexelsapp.domain.usecases.GetPhotosUseCase
import com.example.pexelsapp.domain.usecases.SearchByCategoryUseCase
import com.example.pexelsapp.ui.entities.CollectionUi
import com.example.pexelsapp.ui.entities.PhotoUi
import com.example.pexelsapp.ui.statesUi.PexelPageState
import com.example.pexelsapp.ui.mappers.toUi
import com.example.pexelsapp.ui.utils.NetworkUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase,
    private val getFeaturedCollectionsUseCase: GetFeaturedCollectionsUseCase,
    private val searchByCategoryUseCase: SearchByCategoryUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<PexelPageState>(PexelPageState.Loading)
    val uiState: StateFlow<PexelPageState> = _uiState.asStateFlow()

    private val _featuredCollections = MutableStateFlow<List<CollectionUi>>(emptyList())
    val featuredCollections: StateFlow<List<CollectionUi>> = _featuredCollections.asStateFlow()

    private var cachedPhotos: List<PhotoUi> = emptyList()
    private var lastQuery: String? = null


    fun getPhotos(page: Int, context: Context) {
        viewModelScope.launch(Dispatchers.Main) {
            _uiState.value = PexelPageState.Loading

            try {
                val photos = getPhotosUseCase(page).map { it.toUi() }
                cachedPhotos = photos
                if (photos.isEmpty()) {
                    _uiState.value = PexelPageState.IsEmpty
                } else {
                    _uiState.value = PexelPageState.Success(photos)
                }
            } catch (_: Exception) {
                val hasInternet = NetworkUtils.isConnected(context)
                if (!hasInternet && cachedPhotos.isNotEmpty()) {
                    _uiState.value = PexelPageState.Success(cachedPhotos)
                    _uiState.value = PexelPageState.NoConnectionWithCache
                } else {
                    _uiState.value = PexelPageState.Error("Problems, with connection")
                }
            }
        }
    }

    fun getCollection() {
        viewModelScope.launch(Dispatchers.Main) {
            val collections = getFeaturedCollectionsUseCase().map { it.toUi() }
            _featuredCollections.value = collections
        }
    }

    fun getFilteredPhotos(category: String, context: Context) {
        if (category.isBlank()) {
            getPhotos(1, context = context)
            return
        }
        viewModelScope.launch(Dispatchers.Main) {
            _uiState.value = PexelPageState.Loading
            try {
                val filteredPhotos = searchByCategoryUseCase(category).map { it.toUi() }
                if (filteredPhotos.isEmpty()) {
                    _uiState.value = PexelPageState.IsEmpty
                } else {
                    _uiState.value = PexelPageState.Success(filteredPhotos)
                }
            } catch (_: Exception) {
                _uiState.value = PexelPageState.Error("Problems, with connection")
            }
        }
    }
    fun retry(context: Context) {
        lastQuery?.let {
            if (it.isNotBlank()) getFilteredPhotos(it, context)
            else getPhotos(1, context)
        } ?: getPhotos(1, context)
    }

}