package com.example.pexelsapp.domain.repository

import com.example.pexelsapp.domain.entities.Photo
import kotlinx.coroutines.flow.Flow

interface LocalPhotoRepository {

    suspend fun getAllPhotos(): List<Photo>

    fun getPhotoById(photoId:Int): Flow<Photo>

    suspend fun insertPhotos(photos:List<Photo>)

    suspend fun clearAllPhotos()

    suspend fun clearExpiredPhotos(currentTime:Long, expiration:Long)

    suspend fun searchByCategory(category:String): List<Photo>

    suspend fun updateBookmark(photo: Photo)
}