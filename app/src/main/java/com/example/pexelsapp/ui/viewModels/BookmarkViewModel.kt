package com.example.pexelsapp.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pexelsapp.domain.usecases.GetPhotosUseCase
import com.example.pexelsapp.ui.entities.PhotoUi
import com.example.pexelsapp.ui.mappers.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase
): ViewModel() {

    private val _state = MutableStateFlow<List<PhotoUi>>(emptyList())
    val state = _state.asStateFlow()

    fun loadPhotos(page:Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val photos = getPhotosUseCase(page)
            _state.value = photos.filter { it.bookmarked }.map { it.toUi() }
        }
    }

}