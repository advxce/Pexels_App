package com.example.pexelsapp.ui.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pexelsapp.domain.usecases.GetFeaturedCollectionUseCase
import com.example.pexelsapp.domain.usecases.GetPhotosUseCase
import com.example.pexelsapp.domain.usecases.SearchByCategoryUseCase
import com.example.pexelsapp.ui.entities.CollectionUi
import com.example.pexelsapp.ui.statesUi.PexelPageState
import com.example.pexelsapp.ui.mappers.toUi
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
    private val getFeaturedCollectionUseCase: GetFeaturedCollectionUseCase,
    private val searchByCategoryUseCase: SearchByCategoryUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<PexelPageState>(PexelPageState.Loading)
    val uiState: StateFlow<PexelPageState> = _uiState.asStateFlow()

    private val _featuredCollections = MutableStateFlow<List<CollectionUi>>(emptyList())
    val featuredCollections: StateFlow<List<CollectionUi>> = _featuredCollections.asStateFlow()

    fun getPhotos(page: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            _uiState.value = PexelPageState.Loading
            Log.i("State", "loading ${_uiState.value}")
            try {
                val photos = getPhotosUseCase(page).map { it.toUi() }
                if (photos.isEmpty()) {
                    _uiState.value = PexelPageState.isEmpty
                } else {
                    _uiState.value = PexelPageState.Success(photos)
                    Log.i("State", "success ${_uiState.value}")
                }
            } catch (_: Exception) {
                _uiState.value = PexelPageState.Error("Problems, with connection")
            }
        }
    }

    fun getCollection() {
        viewModelScope.launch(Dispatchers.Main) {
            val collections = getFeaturedCollectionUseCase().map { it.toUi() }
            _featuredCollections.value = collections
        }
    }

    fun getFilteredPhotos(category: String) {
        if (category.isBlank()) {
            getPhotos(1)
            return
        }
        viewModelScope.launch(Dispatchers.Main) {
            _uiState.value = PexelPageState.Loading
            try {
                val filteredPhotos = searchByCategoryUseCase(category).map { it.toUi() }
                if (filteredPhotos.isEmpty()) {
                    _uiState.value = PexelPageState.isEmpty
                } else {
                    _uiState.value = PexelPageState.Success(filteredPhotos)
                }
            } catch (_: Exception) {
                _uiState.value = PexelPageState.Error("Problems, with connection")
            }
        }
    }

}