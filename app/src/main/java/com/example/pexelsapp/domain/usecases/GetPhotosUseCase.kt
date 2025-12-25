package com.example.pexelsapp.domain.usecases

import com.example.pexelsapp.domain.entities.PexelPage
import com.example.pexelsapp.domain.repository.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetPhotosUseCase {
    suspend operator fun invoke(page: Int): PexelPage
}

class GetPhotosUseCaseImpl @Inject constructor(
    private val remoteRepository: RemoteRepository
) : GetPhotosUseCase {
    override suspend fun invoke(page: Int): PexelPage = withContext(Dispatchers.IO) {
        return@withContext remoteRepository.getPhotos(page)
    }
}