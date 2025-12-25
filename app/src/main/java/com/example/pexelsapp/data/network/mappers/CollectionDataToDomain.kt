package com.example.pexelsapp.data.network.mappers

import com.example.pexelsapp.data.network.remoteEntity.featuredCollections.CollectionData
import com.example.pexelsapp.domain.entities.CollectionDomain

fun CollectionData.ToDomain(): CollectionDomain =
    CollectionDomain(
        id = id,
        title = title
    )