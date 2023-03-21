package com.example.test.presentation.entities

import com.example.test.data.local.room.entities.MapDBEntity

data class MapUIEntity(
    var location:String?
)

fun MapUIEntity.toDB() = MapDBEntity(location = location)