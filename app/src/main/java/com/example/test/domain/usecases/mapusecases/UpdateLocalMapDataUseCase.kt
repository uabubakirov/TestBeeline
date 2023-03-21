package com.example.test.domain.usecases.mapusecases

import com.example.test.domain.repositories.MapRepository
import com.example.test.presentation.entities.MapUIEntity
import javax.inject.Inject

class UpdateLocalMapDataUseCase @Inject constructor(
    private val mapRepository: MapRepository
){
    suspend operator fun invoke(mapUIEntity: MapUIEntity) = mapRepository.insertData(mapUIEntity)
}