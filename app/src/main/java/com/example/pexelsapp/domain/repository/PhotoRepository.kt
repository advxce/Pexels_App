package com.example.pexelsapp.domain.repository

import com.example.pexelsapp.domain.entities.Photo
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {
    suspend fun getAllPhotos(currentTime: Long, expiration: Long): List<Photo>

    fun getPhotoById(photoId:Int): Flow<Photo>

    suspend fun searchByCategory(category:String): List<Photo>

    suspend fun updateBookmark(photo: Photo)
}