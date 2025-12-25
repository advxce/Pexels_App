package com.example.pexelsapp.data

import com.example.pexelsapp.data.remoteEntity.featuredCollections.FeaturedCollectionsData
import com.example.pexelsapp.data.remoteEntity.photos.PexelPageData
import com.example.pexelsapp.data.remoteEntity.photos.PhotoData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PexelsApiService {
    @GET("curated")
    suspend fun getPhotos(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 30
    ): PexelPageData

    @GET("search")
    suspend fun searchByCategory(
        @Query("query") category: String
    ): PexelPageData

    @GET("photos/{id}")
    suspend fun getPhotoById(
        @Path("id") id:Int
    ): PhotoData

    @GET("collections/featured")
    suspend fun getFeaturedCollection(
        @Query("per_page") perPage:Int = 7
    ): FeaturedCollectionsData
}