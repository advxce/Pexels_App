package com.example.pexelsapp.domain.usecases

import com.example.pexelsapp.domain.entities.FeaturedCollections
import com.example.pexelsapp.domain.repository.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetFeaturedCollectionUseCase {

    suspend operator fun invoke(): FeaturedCollections

}

class GetFeaturedCollectionUseCaseImpl @Inject constructor(
    private val remoteRepository: RemoteRepository
) : GetFeaturedCollectionUseCase {
    override suspend fun invoke(): FeaturedCollections = withContext(Dispatchers.IO) {
        return@withContext remoteRepository.getFeaturedCollection()
    }
}