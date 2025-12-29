package com.example.pexelsapp.data.database

import com.example.pexelsapp.data.database.dao.PhotoDao
import com.example.pexelsapp.data.mappers.toDomain
import com.example.pexelsapp.data.mappers.toEntity
import com.example.pexelsapp.domain.entities.Photo
import com.example.pexelsapp.domain.repository.LocalPhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalPhotoRepositoryImpl @Inject constructor(
    private val photoDao: PhotoDao
) : LocalPhotoRepository {
    override suspend fun getAllPhotos(): List<Photo> {
        val photos = photoDao.getAllPhotos().map { it.toDomain() }
        return photos
    }

    override fun getPhotoById(photoId: Int): Flow<Photo> =
        photoDao.getPhotoById(photoId).map { it.toDomain() }

    override suspend fun insertPhotos(photos: List<Photo>) {
        val mappedPhotos = photos.map { it.toEntity() }
        photoDao.insertPhotos(mappedPhotos)
    }

    override suspend fun clearAllPhotos() {
        photoDao.clearAllPhotos()
    }

    override suspend fun clearExpiredPhotos(currentTime: Long, expiration: Long) {
        photoDao.clearExpired(currentTime, expiration)
    }
    override suspend fun searchByCategory(category: String): List<Photo> {
        return photoDao.searchByCategory(category).map { it.toDomain() }
    }

    override suspend fun updateBookmark(photo: Photo) {
        photoDao.updateBookmark(photo.toEntity())
    }


}