package com.example.pexelsapp.di

import com.example.pexelsapp.data.database.LocalCollectionRepositoryImpl
import com.example.pexelsapp.data.database.LocalPhotoRepositoryImpl
import com.example.pexelsapp.data.network.RemoteRepositoryImpl
import com.example.pexelsapp.domain.repository.LocalCollectionRepository
import com.example.pexelsapp.domain.repository.LocalPhotoRepository
import com.example.pexelsapp.domain.repository.RemoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRemoteRepository(impl: RemoteRepositoryImpl): RemoteRepository

    @Binds
    abstract fun bindLocalPhotoRepository(impl: LocalPhotoRepositoryImpl): LocalPhotoRepository

    @Binds
    abstract fun bindLocalCollectionRepository(impl: LocalCollectionRepositoryImpl): LocalCollectionRepository


}