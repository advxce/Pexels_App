package com.example.pexelsapp.ui.viewModels

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pexelsapp.domain.usecases.GetPhotoByIdUseCase
import com.example.pexelsapp.domain.usecases.UpdateBookmarkUseCase
import com.example.pexelsapp.ui.mappers.toUi
import com.example.pexelsapp.ui.statesUi.PhotoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.core.net.toUri
import com.example.pexelsapp.ui.entities.PhotoUi

@HiltViewModel
class PhotoDetailsViewModel @Inject constructor(
    private val getPhotoByIdUseCase: GetPhotoByIdUseCase,
    private val updateBookmarkUseCase: UpdateBookmarkUseCase,
) : ViewModel() {

    private val _photoState = MutableStateFlow<PhotoState>(PhotoState.Loading)
    val photoState: StateFlow<PhotoState> = _photoState.asStateFlow()




    fun loadPhoto(id: Int) {
        viewModelScope.launch {
            try {
                getPhotoByIdUseCase(id)
                    .collect { photo ->
                        if(photo.src.isNotEmpty() || photo.src.isNotBlank())
                        _photoState.value = PhotoState.Success(photo.toUi())
                        else   _photoState.value = PhotoState.isEmpty
                    }
            } catch (_: Exception){
                _photoState.value = PhotoState.Error("Problem with connection")
            }

        }
    }

    fun downloadImage(context: Context, url: String) {
        val uri = url.toUri()
        val request = DownloadManager.Request(uri)
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
            .setTitle("Downloading image")
            .setDescription("Saving to gallery")
            .setMimeType("image/jpeg")
            .addRequestHeader("User-Agent", "Mozilla/5.0")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(
                Environment.DIRECTORY_PICTURES,
                "pexels_${System.currentTimeMillis()}.jpeg"
            )

        val dm = context.getSystemService(DownloadManager::class.java)
        dm.enqueue(request)
    }
    fun updateBookmark(photoUi: PhotoUi) {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val updatedPhoto = updateBookmarkUseCase(photoUi.id)
                updatePhotoInState(
                    updatedPhoto.toUi().id,
                    newState = updatedPhoto.toUi().bookmarked,
                )
            } catch (_: Exception) {
                _photoState.value = PhotoState.Error("Failed to update")
            }
        }
    }

    private fun updatePhotoInState(photoId: Int, newState: Boolean) {
        val currentState = _photoState.value
        if (currentState is PhotoState.Success) {
            val updatedPhoto =
                if (currentState.photo.id == photoId) currentState.photo.copy(bookmarked = newState)
                else currentState.photo
            _photoState.value = PhotoState.Success(updatedPhoto)
        }
    }

}

