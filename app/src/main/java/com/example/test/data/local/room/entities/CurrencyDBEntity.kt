package com.example.test.data.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.test.presentation.entities.CurrencyUI
import com.example.test.presentation.entities.ValuteUI

@Entity(
    tableName = "currency"
)
data class CurrencyDBEntity(
    @PrimaryKey(autoGenerate = false)
    val id:Long? = 1,
    val value: String?,
    val date: String?,
    val lastSign: String?
)

fun CurrencyDBEntity.toUI() = CurrencyUI(value,date,lastSign)

