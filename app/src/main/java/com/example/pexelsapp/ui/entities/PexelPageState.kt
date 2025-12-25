package com.example.pexelsapp.ui.entities

sealed class PexelPageState {
    data class Success(val list: PexelPageUi): PexelPageState()
    object Loading: PexelPageState()
    data class Error(val msg:String): PexelPageState()
}