package com.example.pexelsapp.domain.repository

import com.example.pexelsapp.data.database.localEntity.CollectionEntity
import com.example.pexelsapp.data.database.localEntity.PhotoEntity
import com.example.pexelsapp.domain.entities.FeaturedCollections
import com.example.pexelsapp.domain.entities.PexelPage
import com.example.pexelsapp.domain.entities.Photo

interface RemoteRepository {

    suspend fun getPhotos(page:Int): List<PhotoEntity>

    suspend fun searchByCategory(category:String): List<PhotoEntity>

    suspend fun getPhotoById(id:Int): PhotoEntity

    suspend fun getFeaturedCollection(): List<CollectionEntity>

}