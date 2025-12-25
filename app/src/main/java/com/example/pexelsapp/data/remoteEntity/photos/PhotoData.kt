package com.example.pexelsapp.data.remoteEntity.photos

import kotlinx.serialization.Serializable

@Serializable
data class PhotoData(
    val id:Int,
    val src: SrcData
)
