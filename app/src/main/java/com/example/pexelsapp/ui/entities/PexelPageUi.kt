package com.example.pexelsapp.ui.entities

import com.example.pexelsapp.data.network.remoteEntity.photos.PhotoData

data class PexelPageUi(
    val page: Int,
    val perPage: Int,
    val photos: List<PhotoUi>
)
