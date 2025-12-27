package com.example.pexelsapp.domain.repository


import com.example.pexelsapp.domain.entities.CollectionDomain

interface LocalCollectionRepository {

    suspend fun getAllCollections(): List<CollectionDomain>

    suspend fun insertCollections(collections:List<CollectionDomain>)

    suspend fun clearAllCollections()

    suspend fun clearExpiredCollections(currentTime: Long, expiration: Long)
}