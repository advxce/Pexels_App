package com.example.pexelsapp.domain.usecases

import com.example.pexelsapp.domain.entities.Photo
import com.example.pexelsapp.domain.repository.LocalPhotoRepository
import kotlinx.coroutines.flow.Flow
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