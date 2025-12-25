package com.example.pexelsapp.di

import com.example.pexelsapp.data.network.RemoteRepositoryImpl
import com.example.pexelsapp.domain.repository.RemoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(impl: RemoteRepositoryImpl): RemoteRepository

}