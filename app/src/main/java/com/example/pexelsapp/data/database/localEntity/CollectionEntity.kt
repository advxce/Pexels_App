package com.example.pexelsapp.data.database.localEntity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "collections")
data class CollectionEntity(
    @PrimaryKey val id: String,
    val title: String,
    val cacheTime:Long,
    val isActive: Boolean = false
)
