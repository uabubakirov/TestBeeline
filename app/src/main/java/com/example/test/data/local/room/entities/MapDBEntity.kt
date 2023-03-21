package com.example.test.data.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.test.presentation.entities.MapUIEntity
import com.example.test.presentation.entities.ValuteUI
import com.yandex.mapkit.geometry.Point
@Entity(tableName = "map_table")
data class MapDBEntity(
    @PrimaryKey(autoGenerate = false)
    val id:Long? = 1,
    val location:String?
    )

fun MapDBEntity.toUI() = MapUIEntity(location)
