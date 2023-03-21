package com.example.test.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.test.data.local.room.dao.CurrencyDao
import com.example.test.data.local.room.dao.MapDao
import com.example.test.data.local.room.entities.CurrencyDBEntity
import com.example.test.data.local.room.entities.MapDBEntity

@Database(version = 1, entities = [
    CurrencyDBEntity::class,
    MapDBEntity::class
])
abstract class AppDataBase : RoomDatabase() {
    abstract fun getMapDao():MapDao

    abstract fun getCurrency():CurrencyDao
}