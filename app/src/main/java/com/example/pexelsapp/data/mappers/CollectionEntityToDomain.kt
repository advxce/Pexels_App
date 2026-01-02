package com.example.pexelsapp.data.mappers

import com.example.pexelsapp.data.database.localEntity.CollectionEntity
import com.example.pexelsapp.domain.entities.CollectionDomain

fun CollectionEntity.toDomain(): CollectionDomain =
    CollectionDomain(
        id = id,
        title = title.substringBefore(":"),
        cacheTime = cacheTime,
        isActive = isActive
    )