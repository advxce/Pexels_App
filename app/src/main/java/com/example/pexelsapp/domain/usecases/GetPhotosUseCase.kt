package com.example.pexelsapp.domain.usecases

import com.example.pexelsapp.domain.entities.Photo
import com.example.pexelsapp.domain.repository.PhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetPhotosUseCase {
    suspend operator fun invoke(page: Int): List<Photo>
}

class GetPhotosUseCaseImpl @Inject constructor(
    private val photoRepository: PhotoRepository
) : GetPhotosUseCase {
    private val cacheExpiration = 60 * 60 * 1000L
    override suspend fun invoke(page: Int): List<Photo> = withContext(Dispatchers.IO) {
        val currentTime = System.currentTimeMillis()

        return@withContext photoRepository.getAllPhotos(currentTime, cacheExpiration)
    }
}