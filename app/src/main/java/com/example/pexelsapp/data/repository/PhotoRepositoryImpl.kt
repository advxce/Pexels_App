package com.example.pexelsapp.data.repository

import com.example.pexelsapp.data.database.dao.PhotoDao
import com.example.pexelsapp.data.mappers.toDomain
import com.example.pexelsapp.data.mappers.toEntity
import com.example.pexelsapp.data.network.PexelsApiService
import com.example.pexelsapp.domain.entities.Photo
import com.example.pexelsapp.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val dao: PhotoDao,
    private val apiService: PexelsApiService
) : PhotoRepository {
    override suspend fun getAllPhotos(currentTime: Long, expiration: Long): List<Photo> {
        dao.clearExpired(currentTime, expiration)
        val cachedPhotos = dao.getAllPhotos().map { it.toDomain() }
        if (cachedPhotos.isNotEmpty()) {
            return cachedPhotos
        }
        val remotePhotos = apiService.getPhotos().photos.map { it.toEntity() }
        val entitiesPhotos = remotePhotos
        dao.insertPhotos(entitiesPhotos)
        return remotePhotos.map { it.toDomain() }
    }

    override fun getPhotoById(photoId: Int): Flow<Photo> = dao.getPhotoById(photoId)
        .onStart {
            val cached = dao.getPhotoById(photoId).firstOrNull()
            if (cached == null) {
                val remotePhoto = apiService.getPhotoById(photoId)
                dao.insertPhotos(listOf(remotePhoto.toEntity()))
            }
        }
        .map { it.toDomain() }

    override suspend fun searchByCategory(category: String): List<Photo> {
        val cachedFilteredPhotos = dao.searchByCategory(category).map { it.toDomain() }
        if(cachedFilteredPhotos.isNotEmpty()){
            return cachedFilteredPhotos
        }
        val remoteFilteredPhotos = apiService.searchByCategory(category).photos.map { it.toEntity() }
        val entityFilteredPhotos = remoteFilteredPhotos
        dao.insertPhotos(entityFilteredPhotos)
        return remoteFilteredPhotos.map { it.toDomain() }
    }

    override suspend fun updateBookmark(photo: Photo) {
        dao.updateBookmark(photo.toEntity())
    }
}