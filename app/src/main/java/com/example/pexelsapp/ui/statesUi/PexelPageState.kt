package com.example.pexelsapp.ui.statesUi

import com.example.pexelsapp.ui.entities.PhotoUi

sealed class PexelPageState {
    data class Success(val list: List<PhotoUi>): PexelPageState()
    object Loading: PexelPageState()
    data class Error(val msg:String): PexelPageState()
    object IsEmpty: PexelPageState()
    object NoConnectionWithCache: PexelPageState()
}