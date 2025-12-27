package com.example.pexelsapp.data.network

import com.example.pexelsapp.data.database.localEntity.CollectionEntity
import com.example.pexelsapp.data.database.localEntity.PhotoEntity
import com.example.pexelsapp.data.mappers.toEntity
import com.example.pexelsapp.domain.repository.RemoteRepository
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val pexelsApiService: PexelsApiService
) : RemoteRepository {
    override suspend fun getPhotos(page: Int): List<PhotoEntity> {
        val pexelPage = pexelsApiService.getPhotos(page = page).photos.map { it.toEntity() }
        return pexelPage
    }

    override suspend fun searchByCategory(category: String): List<PhotoEntity> {
        val filteredPexelPage =
            pexelsApiService.searchByCategory(category).photos.map { it.toEntity() }
        return filteredPexelPage
    }

    override suspend fun getPhotoById(id: Int): PhotoEntity {
        val photo = pexelsApiService.getPhotoById(id).toEntity()
        return photo
    }

    override suspend fun getFeaturedCollection(): List<CollectionEntity> {
        val featuredCollection = pexelsApiService.getFeaturedCollection().collections.map { it.toEntity() }
        return featuredCollection
    }
}