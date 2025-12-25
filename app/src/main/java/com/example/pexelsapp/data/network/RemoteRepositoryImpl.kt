package com.example.pexelsapp.data.network

import com.example.pexelsapp.data.network.mappers.toDomain
import com.example.pexelsapp.domain.entities.FeaturedCollections
import com.example.pexelsapp.domain.entities.PexelPage
import com.example.pexelsapp.domain.entities.Photo
import com.example.pexelsapp.domain.repository.RemoteRepository
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val pexelsApiService: PexelsApiService
) : RemoteRepository {
    override suspend fun getPhotos(page: Int): PexelPage {
        val pexelPage = pexelsApiService.getPhotos(page = page).toDomain()
        return pexelPage
    }

    override suspend fun searchByCategory(category: String): PexelPage {
        val filteredPexelPage = pexelsApiService.searchByCategory(category).toDomain()
        return filteredPexelPage
    }

    override suspend fun getPhotoById(id: Int): Photo {
        val photo = pexelsApiService.getPhotoById(id).toDomain()
        return photo
    }

    override suspend fun getFeaturedCollection(): FeaturedCollections {
        val featuredCollection = pexelsApiService.getFeaturedCollection().toDomain()
        return featuredCollection
    }
}