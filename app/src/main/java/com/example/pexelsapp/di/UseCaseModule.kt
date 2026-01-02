package com.example.pexelsapp.di

import com.example.pexelsapp.domain.repository.CollectionRepository
import com.example.pexelsapp.domain.repository.PhotoRepository
import com.example.pexelsapp.domain.usecases.GetFeaturedCollectionsUseCase
import com.example.pexelsapp.domain.usecases.GetFeaturedCollectionsUseCaseImpl
import com.example.pexelsapp.domain.usecases.GetPhotoByIdUseCase
import com.example.pexelsapp.domain.usecases.GetPhotoByIdUseCaseImpl
import com.example.pexelsapp.domain.usecases.GetPhotosUseCase
import com.example.pexelsapp.domain.usecases.GetPhotosUseCaseImpl

import com.example.pexelsapp.domain.usecases.SearchByCategoryUseCase
import com.example.pexelsapp.domain.usecases.SearchByCategoryUseCaseImpl
import com.example.pexelsapp.domain.usecases.UpdateBookmarkUseCase
import com.example.pexelsapp.domain.usecases.UpdateBookmarkUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetFeaturedCollectionUseCase(
        collectionRepository: CollectionRepository
    ): GetFeaturedCollectionsUseCase =
        GetFeaturedCollectionsUseCaseImpl(collectionRepository)

    @Provides
    fun provideGetPhotosByIdUseCase(photoRepository: PhotoRepository): GetPhotoByIdUseCase =
        GetPhotoByIdUseCaseImpl(photoRepository)

    @Provides
    fun provideGetPhotosUseCase(
        photoRepository: PhotoRepository
    ): GetPhotosUseCase =
        GetPhotosUseCaseImpl(photoRepository)

    @Provides
    fun provideSearchByCategoryUseCase(
        photoRepository: PhotoRepository
    ): SearchByCategoryUseCase =
        SearchByCategoryUseCaseImpl(photoRepository)

    @Provides
    fun provideUpdateBookmarkUseCase(
        photoRepository: PhotoRepository
    ): UpdateBookmarkUseCase =
        UpdateBookmarkUseCaseImpl(photoRepository)

}