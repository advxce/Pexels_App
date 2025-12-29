package com.example.pexelsapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pexelsapp.data.database.localEntity.CollectionEntity


@Dao
interface CollectionDao {
    @Query("SELECT * FROM collections")
    suspend fun getAllCollections(): List<CollectionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCollections(collections: List<CollectionEntity>)

    @Query("DELETE FROM collections")
    suspend fun clearAllCollections()

    @Query("DELETE FROM collections WHERE :currentTime - cacheTime > :expiration")
    suspend fun clearExpired(currentTime: Long, expiration: Long)


}
