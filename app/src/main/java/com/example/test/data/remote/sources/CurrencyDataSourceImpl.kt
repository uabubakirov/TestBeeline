package com.example.test.data.remote.sources

import com.example.test.data.remote.apiservices.CurrencyApi
import com.example.test.data.remote.entities.CurrencyModel
import javax.inject.Inject

class CurrencyDataSourceImpl @Inject constructor(
    private val currencyApi: CurrencyApi
) : CurrencyDataSource {

    override suspend fun getData(): CurrencyModel {
        return currencyApi.getData()
    }


}