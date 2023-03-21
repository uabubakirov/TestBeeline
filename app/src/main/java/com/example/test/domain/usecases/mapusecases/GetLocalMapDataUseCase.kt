package com.example.test.domain.usecases.mapusecases

import com.example.test.domain.repositories.MapRepository
import javax.inject.Inject

class GetLocalMapDataUseCase @Inject constructor(
    private val repository: MapRepository
){
    operator fun invoke()= repository.getLocalData()
}