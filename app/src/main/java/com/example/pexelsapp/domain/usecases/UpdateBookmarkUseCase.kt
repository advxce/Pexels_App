package com.example.pexelsapp.domain.usecases

import com.example.pexelsapp.domain.entities.Photo
import com.example.pexelsapp.domain.repository.LocalPhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface UpdateBookmarkUseCase {
    suspend operator fun invoke(photoId: Int): Photo
}

class UpdateBookmarkUseCaseImpl @Inject constructor(
    private val localPhotoRepository: LocalPhotoRepository
) : UpdateBookmarkUseCase{
    override suspend fun invoke(photoId: Int): Photo = withContext(Dispatchers.IO) {
        val currentPhoto = localPhotoRepository.getPhotoById(photoId).first()
        val updatedPhoto = currentPhoto.copy(bookmarked = !currentPhoto.bookmarked)
        localPhotoRepository.updateBookmark(updatedPhoto)
        return@withContext updatedPhoto
    }

}