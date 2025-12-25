package com.example.pexelsapp.data.network.remoteEntity.featuredCollections

import kotlinx.serialization.Serializable

@Serializable
data class FeaturedCollectionsData(
    val collections: List<CollectionData>
)
