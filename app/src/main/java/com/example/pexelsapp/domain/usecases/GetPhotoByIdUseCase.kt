package com.example.pexelsapp.domain.usecases

import com.example.pexelsapp.data.mappers.toDomain
import com.example.pexelsapp.domain.entities.Photo
import com.example.pexelsapp.domain.repository.LocalPhotoRepository
import com.example.pexelsapp.domain.repository.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetPhotoByIdUseCase {

    operator fun invoke(id: Int): Flow<Photo>

}

class GetPhotoByIdUseCaseImpl @Inject constructor(
    private val localPhotoRepository: LocalPhotoRepository
) : GetPhotoByIdUseCase {
    override fun invoke(id: Int): Flow<Photo> =
         localPhotoRepository.getPhotoById(id)

}