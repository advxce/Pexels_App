package com.example.pexelsapp.di

import android.content.Context
import androidx.room.Room
import com.example.pexelsapp.data.database.PexelsAppDatabase
import com.example.pexelsapp.data.database.dao.CollectionDao
import com.example.pexelsapp.data.database.dao.PhotoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): PexelsAppDatabase =
        Room.databaseBuilder(
            context = context,
            klass = PexelsAppDatabase::class.java,
            name = "pexels.db"
        )
            .addMigrations(PexelsAppDatabase.MIGRATION_1_2).build()

    @Provides
    fun provideCollectionDao(db: PexelsAppDatabase): CollectionDao = db.collectionDao()

    @Provides
    fun providePhotoDao(db: PexelsAppDatabase): PhotoDao = db.photoDao()
}