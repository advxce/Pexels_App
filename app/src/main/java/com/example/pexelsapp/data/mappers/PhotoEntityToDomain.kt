package com.example.pexelsapp.data.mappers

import com.example.pexelsapp.data.database.localEntity.PhotoEntity
import com.example.pexelsapp.domain.entities.Photo

fun PhotoEntity.toDomain(): Photo =
    Photo(
        id = id,
        src = imageUrl,
        photographer = photographer,
        bookmarked = bookmarked,
        cacheTime = cacheTime,
        category = category
    )