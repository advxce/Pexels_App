package com.example.pexelsapp.domain.repository

import com.example.pexelsapp.domain.entities.FeaturedCollections
import com.example.pexelsapp.domain.entities.PexelPage
import com.example.pexelsapp.domain.entities.Photo

interface RemoteRepository {

    suspend fun getPhotos(page:Int): PexelPage

    suspend fun searchByCategory(category:String): PexelPage

    suspend fun getPhotoById(id:Int):Photo

    suspend fun getFeaturedCollection(): FeaturedCollections

}