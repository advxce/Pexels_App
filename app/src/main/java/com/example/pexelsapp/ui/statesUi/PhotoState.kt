package com.example.pexelsapp.ui.statesUi

import com.example.pexelsapp.ui.entities.PhotoUi

sealed class PhotoState {
    data class Success(val photo: PhotoUi): PhotoState()
    object Loading: PhotoState()
    object isEmpty: PhotoState()
    data class Error(val msg:String): PhotoState()
}