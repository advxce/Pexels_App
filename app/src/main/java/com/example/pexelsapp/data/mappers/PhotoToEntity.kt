package com.example.pexelsapp.data.mappers

import com.example.pexelsapp.data.database.localEntity.PhotoEntity
import com.example.pexelsapp.domain.entities.Photo

fun Photo.toEntity(): PhotoEntity =
    PhotoEntity(
        id = id,
        imageUrl = src,
        photographer = photographer,
        bookmarked = bookmarked,
        cacheTime = cacheTime,
        category = category
    )