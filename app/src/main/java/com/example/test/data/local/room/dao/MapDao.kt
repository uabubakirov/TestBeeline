package com.example.test.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.test.data.local.room.entities.CurrencyDBEntity
import com.example.test.data.local.room.entities.MapDBEntity
@Dao
interface MapDao {
    //language=sql
    @Query("SELECT * FROM map_table")
    suspend fun getData(): MapDBEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateData(mapDBEntity: MapDBEntity)
}