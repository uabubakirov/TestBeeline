package com.example.test.data.repositories

import com.example.test.data.local.room.dao.CurrencyDao
import com.example.test.data.remote.entities.toUI
import com.example.test.data.remote.sources.CurrencyDataSource
import com.example.test.domain.repositories.CurrencyRepository
import com.example.test.presentation.entities.CurrencyUI
import com.example.test.presentation.entities.toDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val currencyDataSource: CurrencyDataSource,
    private val currencyDao: CurrencyDao
): BaseRepository(),CurrencyRepository{

    override fun getRemoteData() = doRequest {
        currencyDataSource.getData().toUI()
    }

    override suspend fun updateLocalData(currencyUI: CurrencyUI){
        withContext(Dispatchers.IO){
            currencyDao.updateData(currencyUI.toDB())
        }

    }

    override fun getLocalData()= doLocalRequest{
        currencyDao.getData()
    }
}


