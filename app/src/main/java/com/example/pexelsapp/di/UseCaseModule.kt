package com.example.pexelsapp.di

import com.example.pexelsapp.domain.repository.RemoteRepository
import com.example.pexelsapp.domain.usecases.GetFeaturedCollectionUseCase
import com.example.pexelsapp.domain.usecases.GetFeaturedCollectionUseCaseImpl
import com.example.pexelsapp.domain.usecases.GetPhotoByIdUseCase
import com.example.pexelsapp.domain.usecases.GetPhotoByIdUseCaseImpl
import com.example.pexelsapp.domain.usecases.GetPhotosUseCase
import com.example.pexelsapp.domain.usecases.GetPhotosUseCaseImpl
import com.example.pexelsapp.domain.usecases.SearchByCategoryUseCase
import com.example.pexelsapp.domain.usecases.SearchByCategoryUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetFeaturedCollectionUseCase(repository: RemoteRepository) : GetFeaturedCollectionUseCase =
        GetFeaturedCollectionUseCaseImpl(repository)

    @Provides
    fun provideGetPhotosByIdUseCase(repository: RemoteRepository) : GetPhotoByIdUseCase =
        GetPhotoByIdUseCaseImpl(repository)

    @Provides
    fun provideGetPhotosUseCase(repository : RemoteRepository): GetPhotosUseCase =
        GetPhotosUseCaseImpl(repository)

    @Provides
    fun provideSearchByCategoryUseCase(repository: RemoteRepository): SearchByCategoryUseCase =
        SearchByCategoryUseCaseImpl(repository)
}