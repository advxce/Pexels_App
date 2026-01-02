package com.example.pexelsapp.domain.repository

import com.example.pexelsapp.domain.entities.CollectionDomain

interface CollectionRepository {
    suspend fun getAllCollections(currentTime: Long, expiration: Long): List<CollectionDomain>
}