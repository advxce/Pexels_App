package com.example.pexelsapp.data.mappers

import com.example.pexelsapp.data.database.localEntity.PhotoEntity
import com.example.pexelsapp.data.network.remoteEntity.photos.PhotoData

fun PhotoData.toEntity(): PhotoEntity{
    val now = System.currentTimeMillis()
    return PhotoEntity(
        id = id,
        imageUrl = src.original,
        cacheTime = now,
        photographer = photographer,
    )
}