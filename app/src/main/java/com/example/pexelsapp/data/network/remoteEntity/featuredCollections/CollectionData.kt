package com.example.pexelsapp.data.network.remoteEntity.featuredCollections

import kotlinx.serialization.Serializable

@Serializable
data class CollectionData(
    val id: String,
    val title: String
)
