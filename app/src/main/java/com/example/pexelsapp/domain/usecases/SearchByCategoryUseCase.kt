package com.example.pexelsapp.domain.usecases

import com.example.pexelsapp.data.mappers.toDomain
import com.example.pexelsapp.domain.entities.Photo
import com.example.pexelsapp.domain.repository.LocalPhotoRepository
import com.example.pexelsapp.domain.repository.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface SearchByCategoryUseCase {

    suspend operator fun invoke(category: String): List<Photo>

}

class SearchByCategoryUseCaseImpl @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val localPhotosRepository: LocalPhotoRepository
) : SearchByCategoryUseCase {
    override suspend fun invoke(category: String): List<Photo> = withContext(Dispatchers.IO) {
        val cachedAndFilteredPhotos = localPhotosRepository.searchByCategory(category)
        if(cachedAndFilteredPhotos.isNotEmpty()){
            return@withContext cachedAndFilteredPhotos
        }
        val remoteFilteredPhotos = remoteRepository.searchByCategory(category).map { it.toDomain() }
        localPhotosRepository.insertPhotos(remoteFilteredPhotos)
        return@withContext remoteFilteredPhotos
    }
}