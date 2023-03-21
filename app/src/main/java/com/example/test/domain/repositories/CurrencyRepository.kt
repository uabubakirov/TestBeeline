package com.example.test.domain.repositories

import com.example.test.data.local.room.entities.CurrencyDBEntity
import com.example.test.data.remote.utils.Resource
import com.example.test.presentation.entities.CurrencyUI
import com.example.test.presentation.entities.ValuteUI
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {
    suspend fun updateLocalData(currencyUI: CurrencyUI)
    fun getLocalData(): Flow<Resource<CurrencyDBEntity>>
    fun getRemoteData(): Flow<Resource<ValuteUI>>
}