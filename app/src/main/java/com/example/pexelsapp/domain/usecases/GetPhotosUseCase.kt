package com.example.pexelsapp.domain.usecases

import android.util.Log
import com.example.pexelsapp.data.mappers.toDomain
import com.example.pexelsapp.domain.entities.Photo
import com.example.pexelsapp.domain.repository.LocalPhotoRepository
import com.example.pexelsapp.domain.repository.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetPhotosUseCase {
    suspend operator fun invoke(page: Int): List<Photo>
}

class GetPhotosUseCaseImpl @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val localPhotoRepository: LocalPhotoRepository
) : GetPhotosUseCase {
    private val cacheExpiration = 60 * 60 * 1000L
    override suspend fun invoke(page: Int): List<Photo> = withContext(Dispatchers.IO) {
        val now = System.currentTimeMillis()
        localPhotoRepository.clearExpiredPhotos(now, cacheExpiration)
        val cachedPhotos = localPhotoRepository.getAllPhotos()
        if (cachedPhotos.isNotEmpty()) {
            return@withContext cachedPhotos
        }
        Log.i("Data", "cached ${cachedPhotos.size}")
        val remotePhotos = remoteRepository.getPhotos(page).map { it.toDomain() }
        val entitiesPhotos = remotePhotos
        localPhotoRepository.insertPhotos(entitiesPhotos)
        Log.i("Data", "remote ${remotePhotos.size}")
        return@withContext remotePhotos
    }
}