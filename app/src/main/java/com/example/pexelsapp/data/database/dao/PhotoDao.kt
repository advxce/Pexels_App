package com.example.pexelsapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.pexelsapp.data.database.localEntity.PhotoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDao {

    @Query("SELECT * FROM photos")
    suspend fun getAllPhotos(): List<PhotoEntity>

    @Query("SELECT * FROM photos WHERE id = :photoId")
    fun getPhotoById(photoId: Int): Flow<PhotoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotos(photos: List<PhotoEntity>)

    @Query("DELETE FROM photos")
    suspend fun clearAllPhotos()

    @Query("DELETE FROM photos WHERE :currentTime - cacheTime > :expiration")
    suspend fun clearExpired(currentTime: Long, expiration: Long)

    @Query("SELECT * FROM photos WHERE category LIKE :category || '%'")
    suspend fun searchByCategory(category: String): List<PhotoEntity>

    @Update
    suspend fun updateBookmark(photo: PhotoEntity)

}