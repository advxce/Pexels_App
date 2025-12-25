package com.example.pexelsapp.data.network.mappers

import com.example.pexelsapp.data.network.remoteEntity.photos.PhotoData
import com.example.pexelsapp.domain.entities.Photo

fun PhotoData.toDomain(): Photo =
    Photo(
        id = id,
        src = src.original
    )