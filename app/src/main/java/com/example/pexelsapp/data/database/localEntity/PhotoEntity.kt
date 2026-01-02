package com.example.pexelsapp.data.database.localEntity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photos")
data class PhotoEntity(
    @PrimaryKey val id: Int,
    val imageUrl: String,
    val photographer:String,
    val bookmarked: Boolean = false,
    val cacheTime: Long,
    val category:String = "",
)
