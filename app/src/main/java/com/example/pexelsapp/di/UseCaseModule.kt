package com.example.pexelsapp.di

import com.example.pexelsapp.domain.repository.LocalCollectionRepository
import com.example.pexelsapp.domain.repository.LocalPhotoRepository
import com.example.pexelsapp.domain.repository.RemoteRepository
import com.example.pexelsapp.domain.usecases.GetFeaturedCollectionUseCase
import com.example.pexelsapp.domain.usecases.GetFeaturedCollectionUseCaseImpl
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
        remoteRepository: RemoteRepository,
        localCollectionRepository: LocalCollectionRepository
    ): GetFeaturedCollectionUseCase =
        GetFeaturedCollectionUseCaseImpl(remoteRepository, localCollectionRepository)

    @Provides
    fun provideGetPhotosByIdUseCase(localPhotoRepository: LocalPhotoRepository): GetPhotoByIdUseCase =
        GetPhotoByIdUseCaseImpl(localPhotoRepository)

    @Provides
    fun provideGetPhotosUseCase(
        remoteRepository: RemoteRepository,
        localPhotoRepository: LocalPhotoRepository
    ): GetPhotosUseCase =
        GetPhotosUseCaseImpl(remoteRepository, localPhotoRepository)

    @Provides
    fun provideSearchByCategoryUseCase(
        remoteRepository: RemoteRepository,
        localPhotoRepository: LocalPhotoRepository
    ): SearchByCategoryUseCase =
        SearchByCategoryUseCaseImpl(remoteRepository, localPhotoRepository)

    @Provides
    fun provideUpdateBookmarkUseCase(
        localPhotoRepository: LocalPhotoRepository
    ): UpdateBookmarkUseCase =
        UpdateBookmarkUseCaseImpl(localPhotoRepository)

}