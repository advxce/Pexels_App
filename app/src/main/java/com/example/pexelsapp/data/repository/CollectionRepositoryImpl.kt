package com.example.pexelsapp.data.repository

import com.example.pexelsapp.data.database.dao.CollectionDao
import com.example.pexelsapp.data.mappers.toDomain
import com.example.pexelsapp.data.mappers.toEntity
import com.example.pexelsapp.data.network.PexelsApiService
import com.example.pexelsapp.domain.entities.CollectionDomain
import com.example.pexelsapp.domain.repository.CollectionRepository
import javax.inject.Inject

class CollectionRepositoryImpl @Inject constructor(
    private val dao: CollectionDao,
    private val apiService: PexelsApiService
): CollectionRepository {
    override suspend fun getAllCollections(currentTime: Long, expiration: Long): List<CollectionDomain> {
        dao.clearExpired(currentTime, expiration)
        val cachedCollections = dao.getAllCollections().map { it.toDomain() }
        if(cachedCollections.isNotEmpty()){
            return cachedCollections
        }
        val remoteCollections = apiService.getFeaturedCollection().collections.map { it.toEntity() }
        val entityCollections = remoteCollections
        dao.insertCollections(entityCollections)
        return remoteCollections.map { it.toDomain() }
    }

}