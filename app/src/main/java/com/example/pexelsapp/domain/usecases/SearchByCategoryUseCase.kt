package com.example.pexelsapp.domain.usecases

import com.example.pexelsapp.domain.entities.PexelPage
import com.example.pexelsapp.domain.repository.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface SearchByCategoryUseCase {

    suspend operator fun invoke(category: String): PexelPage

}

class SearchByCategoryUseCaseImpl @Inject constructor(
    private val remoteRepository: RemoteRepository
) : SearchByCategoryUseCase {
    override suspend fun invoke(category: String): PexelPage = withContext(Dispatchers.IO) {
        return@withContext remoteRepository.searchByCategory(category)
    }
}