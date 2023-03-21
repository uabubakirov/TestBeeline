package com.example.test.domain.repositories

import com.example.test.data.local.room.entities.MapDBEntity
import com.example.test.data.remote.utils.Resource
import com.example.test.presentation.entities.MapUIEntity
import com.example.test.presentation.entities.toDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

interface MapRepository {

    suspend fun insertData(mapUIEntity: MapUIEntity)

    fun getLocalData(): Flow<Resource<MapDBEntity>>
}