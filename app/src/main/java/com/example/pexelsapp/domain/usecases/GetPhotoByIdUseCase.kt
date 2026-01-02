package com.example.pexelsapp.domain.usecases

import com.example.pexelsapp.domain.entities.Photo
import com.example.pexelsapp.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetPhotoByIdUseCase {

    operator fun invoke(id: Int): Flow<Photo>

}

class GetPhotoByIdUseCaseImpl @Inject constructor(
    private val photoRepository: PhotoRepository
) : GetPhotoByIdUseCase {
    override fun invoke(id: Int): Flow<Photo> =
         photoRepository.getPhotoById(id)

}