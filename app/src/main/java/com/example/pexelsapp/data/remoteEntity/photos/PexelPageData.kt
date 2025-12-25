package com.example.pexelsapp.data.remoteEntity.photos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PexelPageData(
    val page: Int,
    @SerialName("per_page") val perPage: Int,
    val photos: List<PhotoData>
)
