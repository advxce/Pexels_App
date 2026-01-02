package com.example.pexelsapp.data.mappers

import com.example.pexelsapp.data.database.localEntity.CollectionEntity
import com.example.pexelsapp.domain.entities.CollectionDomain

fun CollectionDomain.toEntity(): CollectionEntity =
    CollectionEntity(
        id = id,
        title = title,
        cacheTime = cacheTime,
        isActive = isActive
    )