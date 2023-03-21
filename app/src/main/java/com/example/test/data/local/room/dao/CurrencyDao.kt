package com.example.test.data.local.room.dao


import androidx.room.*
import com.example.test.data.local.room.entities.CurrencyDBEntity

@Dao
interface CurrencyDao {
    //language=sql
    @Query("SELECT * FROM currency")
    suspend fun getData():CurrencyDBEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateData(currencyDBEntity: CurrencyDBEntity)


}