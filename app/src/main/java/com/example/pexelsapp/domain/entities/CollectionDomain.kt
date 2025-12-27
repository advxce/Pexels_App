package com.example.pexelsapp.domain.entities

data class CollectionDomain(
    val id: String,
    val title: String,
    val cacheTime: Long,
    val isActive: Boolean = false
)