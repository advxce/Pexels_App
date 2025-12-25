package com.example.pexelsapp.data.remoteEntity.featuredCollections

import kotlinx.serialization.Serializable

@Serializable
data class FeaturedCollectionsData(
    val collections: List<CollectionData>
)
