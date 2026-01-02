package com.example.pexelsapp.data.mappers

import com.example.pexelsapp.data.database.localEntity.CollectionEntity
import com.example.pexelsapp.data.network.remoteEntity.featuredCollections.CollectionData

fun CollectionData.toEntity(): CollectionEntity{
    val now = System.currentTimeMillis()
    return CollectionEntity(
        id = id,
        title = title.substringBefore(":"),
        cacheTime = now,
    )
}