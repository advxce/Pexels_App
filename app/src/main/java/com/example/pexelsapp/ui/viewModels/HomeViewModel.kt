package com.example.pexelsapp.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pexelsapp.domain.usecases.GetFeaturedCollectionUseCase
import com.example.pexelsapp.domain.usecases.GetPhotosUseCase
import com.example.pexelsapp.domain.usecases.SearchByCategoryUseCase
import com.example.pexelsapp.ui.entities.PexelPageState
import com.example.pexelsapp.ui.entities.FeaturedCollectionsUi
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
): ViewModel() {

    private val _uiState = MutableStateFlow<PexelPageState>(PexelPageState.Loading)
    val uiState: StateFlow<PexelPageState> = _uiState.asStateFlow()

    private val _featuredCollections = MutableStateFlow<FeaturedCollectionsUi?>(null)
    val featuredCollections: StateFlow<FeaturedCollectionsUi?> = _featuredCollections.asStateFlow()

    fun getPhotos(page:Int){
        viewModelScope.launch(Dispatchers.Main) {
            _uiState.value = PexelPageState.Loading
            try {
                val photos = getPhotosUseCase(page)
                _uiState.value = PexelPageState.Success(photos.toUi())
                //сделать проверку на пустоту
            } catch (_:Exception){
                _uiState.value = PexelPageState.Error("Problems, with connection")
            }
        }
    }

    fun getCollection(){
        viewModelScope.launch(Dispatchers.Main) {
            val collections = getFeaturedCollectionUseCase()
            _featuredCollections.value = collections.toUi()
        }
    }

    fun getFilteredPhotos(category:String){
        viewModelScope.launch(Dispatchers.Main) {
            _uiState.value = PexelPageState.Loading
            try {
                val filteredPhotos = searchByCategoryUseCase(category).toUi()
                _uiState.value = PexelPageState.Success(filteredPhotos)
                //сделать проверку на пустоту
            } catch (_:Exception){
                _uiState.value = PexelPageState.Error("Problems, with connection")
            }
        }
    }

}