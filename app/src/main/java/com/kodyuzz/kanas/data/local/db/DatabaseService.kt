package com.kodyuzz.kanas.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kodyuzz.kanas.data.local.db.dao.DummyDao
import com.kodyuzz.kanas.data.local.db.entity.DummyEntity
import javax.inject.Singleton

@Singleton
@Database(
    entities = [
        DummyEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class DatabaseService:RoomDatabase(){
    abstract fun dummyDao(): DummyDao
}