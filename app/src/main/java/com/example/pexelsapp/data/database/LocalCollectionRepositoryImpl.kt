package com.example.pexelsapp.data.database

import com.example.pexelsapp.data.database.dao.CollectionDao
import com.example.pexelsapp.data.mappers.toDomain
import com.example.pexelsapp.data.mappers.toEntity
import com.example.pexelsapp.domain.entities.CollectionDomain
import com.example.pexelsapp.domain.repository.LocalCollectionRepository
import javax.inject.Inject

class LocalCollectionRepositoryImpl @Inject constructor(
    private val collectionDao: CollectionDao
): LocalCollectionRepository {
    override suspend fun getAllCollections(): List<CollectionDomain> {
        return collectionDao.getAllCollections().map { it.toDomain() }
    }

    override suspend fun insertCollections(collections: List<CollectionDomain>) {
        val mappedCollections = collections.map { it.toEntity() }
        collectionDao.insertCollections(mappedCollections)
    }

    override suspend fun clearAllCollections() {
        collectionDao.clearAllCollections()
    }

    override suspend fun clearExpiredCollections(
        currentTime: Long,
        expiration: Long
    ) {
        collectionDao.clearExpired(currentTime, expiration)
    }

}