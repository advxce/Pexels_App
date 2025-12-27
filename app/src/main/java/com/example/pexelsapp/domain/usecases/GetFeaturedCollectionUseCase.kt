package com.example.pexelsapp.domain.usecases

import com.example.pexelsapp.data.mappers.toDomain
import com.example.pexelsapp.domain.entities.CollectionDomain
import com.example.pexelsapp.domain.entities.FeaturedCollections
import com.example.pexelsapp.domain.repository.LocalCollectionRepository
import com.example.pexelsapp.domain.repository.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetFeaturedCollectionUseCase {

    suspend operator fun invoke(): List<CollectionDomain>

}

class GetFeaturedCollectionUseCaseImpl @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val localCollectionRepository: LocalCollectionRepository
) : GetFeaturedCollectionUseCase {
    private val cacheExpiration = 60 * 60 * 1000L
    override suspend fun invoke(): List<CollectionDomain> = withContext(Dispatchers.IO) {
        val now = System.currentTimeMillis()
        localCollectionRepository.clearExpiredCollections(now, cacheExpiration)
        val cachedCollections = localCollectionRepository.getAllCollections()
        if(cachedCollections.isNotEmpty()){
            return@withContext cachedCollections
        }
        val remoteCollection = remoteRepository.getFeaturedCollection().map { it.toDomain() }
        val entitiesCollection = remoteCollection
        localCollectionRepository.insertCollections(entitiesCollection)
        return@withContext remoteCollection
    }
}