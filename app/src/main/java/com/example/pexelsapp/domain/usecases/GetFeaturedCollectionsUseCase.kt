package com.example.pexelsapp.domain.usecases

import com.example.pexelsapp.domain.entities.CollectionDomain
import com.example.pexelsapp.domain.repository.CollectionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetFeaturedCollectionsUseCase {

    suspend operator fun invoke(): List<CollectionDomain>

}

class GetFeaturedCollectionsUseCaseImpl @Inject constructor(
    private val collectionRepository: CollectionRepository
) : GetFeaturedCollectionsUseCase {
    private val cacheExpiration = 60 * 60 * 1000L
    override suspend fun invoke(): List<CollectionDomain> = withContext(Dispatchers.IO) {
        val currentTime = System.currentTimeMillis()
        return@withContext collectionRepository.getAllCollections(currentTime, cacheExpiration)
    }
}