package com.example.pexelsapp.data.network.mappers

import com.example.pexelsapp.data.network.remoteEntity.featuredCollections.FeaturedCollectionsData
import com.example.pexelsapp.domain.entities.FeaturedCollections

fun FeaturedCollectionsData.toDomain(): FeaturedCollections =
    FeaturedCollections(
        collections = collections.map { it.ToDomain() }
    )