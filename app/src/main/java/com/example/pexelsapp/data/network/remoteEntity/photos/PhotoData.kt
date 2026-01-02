package com.example.pexelsapp.data.network.remoteEntity.photos

import kotlinx.serialization.Serializable

@Serializable
data class PhotoData(
    val id:Int,
    val src: SrcData,
    val photographer: String,
)
