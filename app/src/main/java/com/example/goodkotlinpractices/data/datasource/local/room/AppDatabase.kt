package com.example.goodkotlinpractices.data.datasource.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.goodkotlinpractices.data.datasource.local.room.dao.CharacterDao
import com.example.goodkotlinpractices.data.datasource.local.room.entities.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}