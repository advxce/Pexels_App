package com.example.pexelsapp.di

import com.example.pexelsapp.data.repository.PhotoRepositoryImpl
import com.example.pexelsapp.data.repository.CollectionRepositoryImpl
import com.example.pexelsapp.domain.repository.CollectionRepository
import com.example.pexelsapp.domain.repository.PhotoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindPhotoRepository(impl: PhotoRepositoryImpl) : PhotoRepository

    @Binds
    abstract fun bindCollectionRepository(impl: CollectionRepositoryImpl): CollectionRepository

}