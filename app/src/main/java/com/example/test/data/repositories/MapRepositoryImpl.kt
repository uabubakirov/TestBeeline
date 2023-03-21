package com.example.test.data.repositories

import com.example.test.data.local.room.dao.MapDao
import com.example.test.domain.repositories.MapRepository
import com.example.test.presentation.entities.MapUIEntity
import com.example.test.presentation.entities.toDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MapRepositoryImpl @Inject constructor(
    private val mapDao: MapDao
):BaseRepository(),MapRepository {

   override suspend fun insertData(mapUIEntity: MapUIEntity){
        withContext(Dispatchers.IO){
            mapDao.updateData(mapUIEntity.toDB())
        }
    }
    override fun getLocalData()= doLocalRequest {
        mapDao.getData()
    }
}