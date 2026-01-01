package com.example.pexelsapp.domain.usecases

import com.example.pexelsapp.domain.entities.Photo
import com.example.pexelsapp.domain.repository.PhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface SearchByCategoryUseCase {

    suspend operator fun invoke(category: String): List<Photo>

}

class SearchByCategoryUseCaseImpl @Inject constructor(
    private val photoRepository: PhotoRepository
) : SearchByCategoryUseCase {
    override suspend fun invoke(category: String): List<Photo> = withContext(Dispatchers.IO) {
        return@withContext photoRepository.searchByCategory(category)
    }
}