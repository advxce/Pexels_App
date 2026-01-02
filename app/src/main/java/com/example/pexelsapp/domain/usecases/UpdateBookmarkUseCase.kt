package com.example.pexelsapp.domain.usecases

import com.example.pexelsapp.domain.entities.Photo
import com.example.pexelsapp.domain.repository.PhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface UpdateBookmarkUseCase {
    suspend operator fun invoke(photoId: Int): Photo
}

class UpdateBookmarkUseCaseImpl @Inject constructor(
    private val photoRepository: PhotoRepository
) : UpdateBookmarkUseCase{
    override suspend fun invoke(photoId: Int): Photo = withContext(Dispatchers.IO) {
        val currentPhoto = photoRepository.getPhotoById(photoId).first()
        val updatedPhoto = currentPhoto.copy(bookmarked = !currentPhoto.bookmarked)
        photoRepository.updateBookmark(updatedPhoto)
        return@withContext updatedPhoto
    }

}