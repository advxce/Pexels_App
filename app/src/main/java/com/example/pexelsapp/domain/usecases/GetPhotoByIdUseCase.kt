package com.example.pexelsapp.domain.usecases

import com.example.pexelsapp.domain.entities.Photo
import com.example.pexelsapp.domain.repository.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetPhotoByIdUseCase {

    suspend operator fun invoke(id: Int): Photo

}

class GetPhotoByIdUseCaseImpl @Inject constructor(
    private val remoteRepository: RemoteRepository
) : GetPhotoByIdUseCase {
    override suspend fun invoke(id: Int): Photo = withContext(Dispatchers.IO) {
        return@withContext remoteRepository.getPhotoById(id)
    }

}