package com.example.pexelsapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.pexelsapp.data.database.dao.CollectionDao
import com.example.pexelsapp.data.database.dao.PhotoDao
import com.example.pexelsapp.data.database.localEntity.CollectionEntity
import com.example.pexelsapp.data.database.localEntity.PhotoEntity

@Database(
    entities = [PhotoEntity::class, CollectionEntity::class],
    version = 1
)
abstract class PexelsAppDatabase: RoomDatabase() {
    abstract fun photoDao(): PhotoDao
    abstract fun collectionDao(): CollectionDao
    companion object{
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "ALTER TABLE photos ADD COLUMN category TEXT NOT NULL DEFAULT ''"
                )
            }
        }
    }
}
